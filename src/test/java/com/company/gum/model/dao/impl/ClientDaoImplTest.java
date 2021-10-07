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
     * Test create client.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testCreateClient() throws DaoException {
        when(instance.createClient(any())).thenReturn(new Client());
        Client result = instance.createClient(new Client());
        Assertions.assertEquals(new Client(), result);
    }

    /**
     * Test edit client.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testEditClient() throws DaoException {
        when(instance.editClient(any())).thenReturn(true);
        boolean result = instance.editClient(new Client());
        Assertions.assertTrue(result);
    }

    /**
     * Test verification.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testVerification() throws DaoException {
        when(instance.verification(anyInt())).thenReturn(true);
        boolean result = instance.verification(10);
        Assertions.assertTrue(result);
    }

    /**
     * Test refill money.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testRefillMoney() throws DaoException {
        when(instance.refillMoney(anyInt(), any())).thenReturn(true);
        boolean result = instance.refillMoney(10, new BigDecimal(100));
        Assertions.assertTrue(result);
    }

    /**
     * Test withdraw money.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testWithdrawMoney() throws DaoException {
        when(instance.withdrawMoney(anyInt(), any())).thenReturn(true);
        boolean result = instance.withdrawMoney(10, new BigDecimal(100));
        Assertions.assertTrue(result);
    }

    /**
     * Test assign discount.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testAssignDiscount() throws DaoException {
        when(instance.assignDiscount(anyInt(), any())).thenReturn(true);
        boolean result = instance.assignDiscount(10, new BigDecimal(100));
        Assertions.assertTrue(result);
    }

    /**
     * Test find client by id.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testFindClientById() throws DaoException {
        when(instance.findClientById(anyInt())).thenReturn(new Client());
        Client result = instance.findClientById(10);
        Assertions.assertEquals(new Client(), result);
    }
}
