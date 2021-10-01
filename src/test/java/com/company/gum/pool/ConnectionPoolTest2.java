package com.company.gum.pool;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

class ConnectionPoolTest2 {
    @Mock
    Logger logger;
    @Mock
    ConnectionPool instance;
    @Mock
    AtomicBoolean isCreated;
    @Mock
    ReentrantLock lock;
    @Mock
    BlockingQueue<ProxyConnection> awaitingConnections;
    @Mock
    BlockingQueue<ProxyConnection> occupiedConnections;
    @InjectMocks
    ConnectionPool connectionPool;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInstance() {
        ConnectionPool result = ConnectionPool.getInstance();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testInitPool() {
        ConnectionPool.initPool();
    }

    @Test
    void testTakeConnection() {
        Connection result = connectionPool.takeConnection();
        Assertions.assertEquals(new ProxyConnection(null), result);
    }

    @Test
    void testReleaseConnection() {
        connectionPool.releaseConnection(new ProxyConnection(null));
    }

    @Test
    void testCloseAllConnections() {
        connectionPool.closeAllConnections();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme