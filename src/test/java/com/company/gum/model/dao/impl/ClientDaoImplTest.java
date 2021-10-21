package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

/**
 * The Class ClientDaoImplTest.
 *
 * @author Vladislav Kuzmich
 */
class ClientDaoImplTest {

    /**
     * The instance.
     */
    @Mock
    ClientDaoImpl instance;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of createClient method, of class ClientDaoImpl.
     */
    @Test
    void testCreateClient() {
        try {
            when(instance.createClient(any())).thenReturn(new Client());
            Client result = instance.createClient(new Client());
            Assertions.assertEquals(new Client(), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of editClient method, of class ClientDaoImpl.
     */
    @Test
    void testEditClient() {
        try {
            when(instance.editClient(any())).thenReturn(true);
            boolean result = instance.editClient(new Client());
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of verification method, of class ClientDaoImpl.
     */
    @Test
    void testVerification() {
        try {
            when(instance.verification(anyInt())).thenReturn(true);
            boolean result = instance.verification(10);
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of refillMoney method, of class ClientDaoImpl.
     */
    @Test
    void testRefillMoney() {
        try {
            when(instance.refillMoney(anyInt(), any())).thenReturn(true);
            boolean result = instance.refillMoney(10, new BigDecimal(100));
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of withdrawMoney method, of class ClientDaoImpl.
     */
    @Test
    void testWithdrawMoney() {
        try {
            when(instance.withdrawMoney(anyInt(), any())).thenReturn(true);
            boolean result = instance.withdrawMoney(10, new BigDecimal(100));
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of assignDiscount method, of class ClientDaoImpl.
     */
    @Test
    void testAssignDiscount() {
        try {
            when(instance.assignDiscount(anyInt(), any())).thenReturn(true);
            boolean result = instance.assignDiscount(10, new BigDecimal(100));
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findClientById method, of class ClientDaoImpl.
     */
    @Test
    void testFindClientById() {
        try {
            when(instance.findClientById(anyInt())).thenReturn(new Client());
            Client result = instance.findClientById(10);
            Assertions.assertEquals(new Client(), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }
}