package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * The Class OrderDaoImplTest.
 *
 * @author Vladislav Kuzmich
 */
class OrderDaoImplTest {

    /**
     * The instance.
     */
    @Mock
    OrderDaoImpl instance;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of createOrder method, of class OrderDaoImpl.
     */
    @Test
    void testCreateOrder() {
        try {
            when(instance.createOrder(any())).thenReturn(new Order());
            Order result = instance.createOrder(new Order());
            Assertions.assertEquals(new Order(), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of deleteOrder method, of class OrderDaoImpl.
     */
    @Test
    void testDeleteOrder() {
        try {
            when(instance.deleteOrder(anyInt())).thenReturn(true);
            boolean result = instance.deleteOrder(10);
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findOrder method, of class OrderDaoImpl.
     */
    @Test
    void testFindOrder() {
        try {
            when(instance.findOrder(anyInt())).thenReturn(new Order());
            Order result = instance.findOrder(10);
            Assertions.assertEquals(new Order(), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findAllOrder method, of class OrderDaoImpl.
     */
    @Test
    void testFindAllOrder() {
        try {
            when(instance.findAllOrder()).thenReturn(Collections.singletonList(new Order()));
            List<Order> result = instance.findAllOrder();
            Assertions.assertEquals(Collections.singletonList(new Order()), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findActiveOrderByTrainer method, of class OrderDaoImpl.
     */
    @Test
    void testFindActiveOrderByTrainer() {
        try {
            when(instance.findActiveOrderByTrainer(anyInt())).thenReturn(Collections.singletonList(new Order()));
            List<Order> result = instance.findActiveOrderByTrainer(10);
            Assertions.assertEquals(Collections.singletonList(new Order()), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findActiveOrderByClient method, of class OrderDaoImpl.
     */
    @Test
    void testFindActiveOrderByClient() {
        try {
            when(instance.findActiveOrderByClient(anyInt())).thenReturn(Collections.singletonList(new Order()));
            List<Order> result = instance.findActiveOrderByClient(10);
            Assertions.assertEquals(Collections.singletonList(new Order()), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of editNutrition method, of class OrderDaoImpl.
     */
    @Test
    void testEditNutrition() {
        try {
            when(instance.editNutrition(anyInt(), anyString())).thenReturn(true);
            boolean result = instance.editNutrition(10, "test");
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of editExercises method, of class OrderDaoImpl.
     */
    @Test
    void testEditExercises() {
        try {
            when(instance.editExercises(anyInt(), anyString())).thenReturn(true);
            boolean result = instance.editExercises(10, "test");
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of editOrderStatus method, of class OrderDaoImpl.
     */
    @Test
    void testEditOrderStatus() {
        try {
            when(instance.editOrderStatus(anyInt(), any())).thenReturn(true);
            boolean result = instance.editOrderStatus(10, Order.OrderStatus.ACCEPTED);
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }
}