package com.company.gum.model.dao;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Order;

import java.util.List;

/**
 * The Interface OrderDao.
 *
 * @author Vladislav Kuzmich
 */
public interface OrderDao {

	/**
	 * The message.
	 */
	String MESSAGE = "Please fill in the required details for the client";

	/**
	 * Creates the order.
	 *
	 * @param order the order
	 * @return the order
	 * @throws DaoException the dao exception
	 */
	Order createOrder(Order order) throws DaoException;

	/**
	 * Delete order.
	 *
	 * @param orderId the order id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean deleteOrder(int orderId) throws DaoException;

	/**
	 * Find order.
	 *
	 * @param orderId the order id
	 * @return the order
	 * @throws DaoException the dao exception
	 */
	Order findOrder(int orderId) throws DaoException;

	/**
	 * Find all order.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Order> findAllOrder() throws DaoException;

	/**
	 * Find active order by trainer.
	 *
	 * @param trainerId the trainer id
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Order> findActiveOrderByTrainer(int trainerId) throws DaoException;

	/**
	 * Find active order by client.
	 *
	 * @param clientId the client id
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Order> findActiveOrderByClient(int clientId) throws DaoException;

	/**
	 * Edits the nutrition.
	 *
	 * @param orderId   the order id
	 * @param nutrition the nutrition
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean editNutrition(int orderId, String nutrition) throws DaoException;

	/**
	 * Edits the exercises.
	 *
	 * @param orderId   the order id
	 * @param exercises the exercises
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean editExercises(int orderId, String exercises) throws DaoException;

	/**
	 * Edits the order status.
	 *
	 * @param orderId     the order id
	 * @param orderStatus the order status
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean editOrderStatus(int orderId, Order.OrderStatus orderStatus) throws DaoException;
}
