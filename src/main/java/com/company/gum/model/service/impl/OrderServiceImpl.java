package com.company.gum.model.service.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.dao.OrderDao;
import com.company.gum.model.dao.impl.OrderDaoImpl;
import com.company.gum.model.entity.Order;
import com.company.gum.model.service.OrderService;

import java.util.List;

/**
 * The Class OrderServiceImpl.
 *
 * @author Vladislav Kuzmich
 */
public class OrderServiceImpl implements OrderService {

	/**
	 * The instance.
	 */
	private static OrderServiceImpl instance;

	/**
	 * The order dao.
	 */
	private final OrderDao orderDao = OrderDaoImpl.getInstance();

	/**
	 * Instantiates a new order service impl.
	 */
	private OrderServiceImpl() {
	}

	/**
	 * Gets the single instance of OrderServiceImpl.
	 *
	 * @return single instance of OrderServiceImpl
	 */
	public static OrderServiceImpl getInstance() {
		if (instance == null) {
			instance = new OrderServiceImpl();
		}
		return instance;
	}

	/**
	 * Creates the order.
	 *
	 * @param order the order
	 * @return the order
	 * @throws ServiceException the service exception
	 */
	@Override
	public Order createOrder(Order order) throws ServiceException {
		Order createdOrder;
		try {
			createdOrder = orderDao.createOrder(order);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return createdOrder;
	}

	/**
	 * Delete order.
	 *
	 * @param orderId the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean deleteOrder(int orderId) throws ServiceException {
		boolean isDeleted;
		try {
			isDeleted = orderDao.deleteOrder(orderId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isDeleted;
	}

	/**
	 * Find order.
	 *
	 * @param orderId the order id
	 * @return the order
	 * @throws ServiceException the service exception
	 */
	@Override
	public Order findOrder(int orderId) throws ServiceException {
		Order order;
		try {
			order = orderDao.findOrder(orderId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return order;
	}

	/**
	 * Find all order.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Order> findAllOrder() throws ServiceException {
		List<Order> orders;
		try {
			orders = orderDao.findAllOrder();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return orders;
	}

	/**
	 * Find active order by trainer.
	 *
	 * @param trainerId the trainer id
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Order> findActiveOrderByTrainer(int trainerId) throws ServiceException {
		List<Order> orders;
		try {
			orders = orderDao.findActiveOrderByTrainer(trainerId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return orders;
	}

	/**
	 * Find active order by client.
	 *
	 * @param clientId the client id
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Order> findActiveOrderByClient(int clientId) throws ServiceException {
		List<Order> orders;
		try {
			orders = orderDao.findActiveOrderByClient(clientId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return orders;
	}

	/**
	 * Edits the nutrition.
	 *
	 * @param orderId   the order id
	 * @param nutrition the nutrition
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean editNutrition(int orderId, String nutrition) throws ServiceException {
		boolean isEdited;
		try {
			isEdited = orderDao.editNutrition(orderId, nutrition);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isEdited;
	}

	/**
	 * Edits the exercises.
	 *
	 * @param orderId   the order id
	 * @param exercises the exercises
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean editExercises(int orderId, String exercises) throws ServiceException {
		boolean isEdited;
		try {
			isEdited = orderDao.editExercises(orderId, exercises);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isEdited;
	}

	/**
	 * Edits the order status.
	 *
	 * @param orderId     the order id
	 * @param orderStatus the order status
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean editOrderStatus(int orderId, Order.OrderStatus orderStatus) throws ServiceException {
		boolean isEdited;
		try {
			isEdited = orderDao.editOrderStatus(orderId, orderStatus);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isEdited;
	}
}