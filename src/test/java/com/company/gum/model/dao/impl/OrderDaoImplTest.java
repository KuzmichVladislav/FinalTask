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
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testCreateOrder() throws DaoException {
		when(instance.createOrder(any())).thenReturn(new Order());
		Order result = instance.createOrder(new Order());
		Assertions.assertEquals(new Order(), result);
	}

	/**
	 * Test of deleteOrder method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testDeleteOrder() throws DaoException {
		when(instance.deleteOrder(anyInt())).thenReturn(true);
		boolean result = instance.deleteOrder(10);
		Assertions.assertTrue(result);
	}

	/**
	 * Test of findOrder method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindOrder() throws DaoException {
		when(instance.findOrder(anyInt())).thenReturn(new Order());
		Order result = instance.findOrder(10);
		Assertions.assertEquals(new Order(), result);
	}

	/**
	 * Test of findAllOrder method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindAllOrder() throws DaoException {
		when(instance.findAllOrder()).thenReturn(Collections.singletonList(new Order()));
		List<Order> result = instance.findAllOrder();
		Assertions.assertEquals(Collections.singletonList(new Order()), result);
	}

	/**
	 * Test of findActiveOrderByTrainer method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindActiveOrderByTrainer() throws DaoException {
		when(instance.findActiveOrderByTrainer(anyInt())).thenReturn(Collections.singletonList(new Order()));
		List<Order> result = instance.findActiveOrderByTrainer(10);
		Assertions.assertEquals(Collections.singletonList(new Order()), result);
	}

	/**
	 * Test of findActiveOrderByClient method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindActiveOrderByClient() throws DaoException {
		when(instance.findActiveOrderByClient(anyInt())).thenReturn(Collections.singletonList(new Order()));
		List<Order> result = instance.findActiveOrderByClient(10);
		Assertions.assertEquals(Collections.singletonList(new Order()), result);
	}

	/**
	 * Test of editNutrition method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testEditNutrition() throws DaoException {
		when(instance.editNutrition(anyInt(), anyString())).thenReturn(true);
		boolean result = instance.editNutrition(10, "test");
		Assertions.assertTrue(result);
	}

	/**
	 * Test of editExercises method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testEditExercises() throws DaoException {
		when(instance.editExercises(anyInt(), anyString())).thenReturn(true);
		boolean result = instance.editExercises(10, "test");
		Assertions.assertTrue(result);
	}

	/**
	 * Test of editOrderStatus method, of class OrderDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testEditOrderStatus() throws DaoException {
		when(instance.editOrderStatus(anyInt(), any())).thenReturn(true);
		boolean result = instance.editOrderStatus(10, Order.OrderStatus.ACCEPTED);
		Assertions.assertTrue(result);
	}
}