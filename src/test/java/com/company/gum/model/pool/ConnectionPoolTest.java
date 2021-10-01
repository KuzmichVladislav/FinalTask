/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.company.gum.model.pool;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author kyzme
 */
class ConnectionPoolTest {

    public ConnectionPoolTest() {
    }

    /**
     * Test of getInstance method, of class ConnectionPool.
     */
    @Test
    void testGetInstance() {
        System.out.println("getInstance");
        ConnectionPool expResult = null;
        ConnectionPool result = ConnectionPool.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initPool method, of class ConnectionPool.
     */
    @Test
    void testInitPool() {
        System.out.println("initPool");
        ConnectionPool.initPool();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of takeConnection method, of class ConnectionPool.
     */
    @Test
    void testTakeConnection() {
        System.out.println("takeConnection");
        ConnectionPool instance = null;
        Connection expResult = null;
        Connection result = instance.takeConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of releaseConnection method, of class ConnectionPool.
     */
    @Test
    void testReleaseConnection() {
        System.out.println("releaseConnection");
        Connection connection = null;
        ConnectionPool instance = null;
        instance.releaseConnection(connection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeAllConnections method, of class ConnectionPool.
     */
    @Test
    void testCloseAllConnections() {
        System.out.println("closeAllConnections");
        ConnectionPool instance = null;
        instance.closeAllConnections();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
