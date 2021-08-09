package com.company.gum.pool;

import com.company.gum.util.PropertyLoader;

import java.lang.reflect.InvocationTargetException;
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

    private static final String PROPERTY_PATH = "db/mysql.properties";
    private int numberOfConnections;
    private BlockingQueue<ProxyConnection> awaitingConnections;
    private Driver driver;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private List<ProxyConnection> occupiedConnections = new ArrayList<>();

    private ConnectionPool() {

        Properties property = PropertyLoader.loadProperty(PROPERTY_PATH);
        numberOfConnections = 5;
        this.awaitingConnections = new ArrayBlockingQueue<>(numberOfConnections);

        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0; i < numberOfConnections; i++) {
            try {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(property.getProperty("db.url"),property));
                awaitingConnections.offer(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            initPool();
            isCreated.set(true);
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
        }
        return connection;
    }

    void releaseConnection(ProxyConnection connection) {
        awaitingConnections.offer(connection);
    }

    public void closeAllConnections() {
        for (int i = 0; i < numberOfConnections; i++) {

            ProxyConnection connection = null;
            try {
                connection = awaitingConnections.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assert connection != null;
            connection.reallyClose();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public int size() {
        return instance.numberOfConnections;
    }
}
