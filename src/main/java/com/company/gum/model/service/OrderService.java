package com.company.gum.model.service;

import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Order;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrderService.
 */
public interface OrderService {

    /**
     * Creates the order.
     *
     * @param order the order
     * @return the order
     * @throws ServiceException the service exception
     */
    Order createOrder(Order order) throws ServiceException;

    /**
     * Delete order.
     *
     * @param orderId the order id
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean deleteOrder(int orderId) throws ServiceException;

    /**
     * Find order.
     *
     * @param orderId the order id
     * @return the order
     * @throws ServiceException the service exception
     */
    Order findOrder(int orderId) throws ServiceException;

    /**
     * Find all order.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllOrder() throws ServiceException;

    /**
     * Find active order by trainer.
     *
     * @param trainerId the trainer id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findActiveOrderByTrainer(int trainerId) throws ServiceException;

    /**
     * Find active order by client.
     *
     * @param clientId the client id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findActiveOrderByClient(int clientId) throws ServiceException;

    /**
     * Edits the nutrition.
     *
     * @param orderId the order id
     * @param nutrition the nutrition
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean editNutrition(int orderId, String nutrition) throws ServiceException;

    /**
     * Edits the exercises.
     *
     * @param orderId the order id
     * @param exercises the exercises
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean editExercises(int orderId, String exercises) throws ServiceException;

    /**
     * Edits the order status.
     *
     * @param orderId the order id
     * @param orderStatus the order status
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean editOrderStatus(int orderId, Order.OrderStatus orderStatus) throws ServiceException;
}
