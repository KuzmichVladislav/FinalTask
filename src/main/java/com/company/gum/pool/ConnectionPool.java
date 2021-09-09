package com.company.gum.pool;

import com.company.gum.util.PropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final int DEFAULT_NUMBER_OF_CONNECTION = 5;
    private static final String PROPERTY_PATH = "database/mysql.properties";
    private static final String URL_PROPERTY = "url";
    private static final String NUMBER_OF_CONNECTIONS = "number_of_connections";
    private static ConnectionPool instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock(true);
    private BlockingQueue<ProxyConnection> awaitingConnections;
    private BlockingQueue<ProxyConnection> occupiedConnections;
    private int numberOfConnections;

    private ConnectionPool() {
        try {
            Properties property = PropertyLoader.loadProperty(PROPERTY_PATH);
            try {
                numberOfConnections = Integer.parseInt(property.getProperty(NUMBER_OF_CONNECTIONS));
            } catch (NumberFormatException e) {
                numberOfConnections = DEFAULT_NUMBER_OF_CONNECTION;
                logger.warn("Incorrect number of connections, set default = {}", numberOfConnections);
            }

            awaitingConnections = new LinkedBlockingDeque<>(numberOfConnections);
            occupiedConnections = new LinkedBlockingDeque<>();
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            for (int i = 0; i < numberOfConnections; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(property.getProperty(URL_PROPERTY), property));
                awaitingConnections.offer(connection);
            }

        } catch (SQLException e) {
            logger.fatal("Fatal error, creation of the connection pool failed", e);
            throw new RuntimeException(e);
        }

    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            lock.lock();
            try {
                initPool();
                isCreated.set(true);
                logger.debug("The connection pool was created with the number of connections {}", instance.numberOfConnections);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static void initPool() {
        if (!isCreated.get()) {
            instance = new ConnectionPool();
        }
    }

    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = awaitingConnections.take();
            occupiedConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Connection interrupted.");
        }
        return connection;
    }

    void releaseConnection(ProxyConnection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            occupiedConnections.remove(connection);
            try {
                awaitingConnections.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Something wrong with current thread ", e);
            }
        } else {
            logger.error("Wrong connection");
            throw new RuntimeException("Wrong connection");
        }
    }

    public void closeAllConnections() {
        for (int i = 0; i < numberOfConnections; i++) {
            try {
                ProxyConnection connection = awaitingConnections.take();
                connection.reallyClose();
            } catch (InterruptedException | SQLException e) {
                logger.error("Close connection pool failed", e);
            }
        }
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("failed to deregister driver", e);
            }
        });
    }

    public int size() {
        return instance.numberOfConnections;
    }
}
