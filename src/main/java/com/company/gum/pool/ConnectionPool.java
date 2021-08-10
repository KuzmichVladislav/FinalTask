package com.company.gum.pool;

import com.company.gum.util.PropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();
    private static final String PROPERTY_PATH = "database/mysql.properties";
    private int numberOfConnections = 10;
    private BlockingQueue<ProxyConnection> awaitingConnections = new ArrayBlockingQueue<>(numberOfConnections);
    private Driver driver;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private List<ProxyConnection> occupiedConnections = new ArrayList<>();

    private ConnectionPool() {

        Properties property = PropertyLoader.loadProperty(PROPERTY_PATH);

        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            for (int i = 0; i < numberOfConnections; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(property.getProperty("db.url"), property));
                awaitingConnections.offer(connection);
            }
        } catch (SQLException e) {
            logger.error("Something wrong with database file!", e);
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            initPool();
            isCreated.set(true);
            logger.debug("connection pool created successfully \"{}\"", instance);
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
            occupiedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.error("Connection interrupted", e);
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    void releaseConnection(ProxyConnection connection) {
        awaitingConnections.offer(connection);
    }

    public void closeAllConnections() {
        for (int i = 0; i < numberOfConnections; i++) {
            ProxyConnection connection;
            try {
                connection = awaitingConnections.take();
                connection.reallyClose();
            } catch (InterruptedException | SQLException e) {
                logger.error("Connection interrupted", e);
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error(e);
                e.printStackTrace();
            }
        }
    }

    public int size() {
        return instance.numberOfConnections;
    }
}