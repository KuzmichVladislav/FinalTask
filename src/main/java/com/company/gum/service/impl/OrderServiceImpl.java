package com.company.gum.service.impl;

import java.util.List;

import com.company.gum.dao.OrderDao;
import com.company.gum.dao.impl.OrderDaoImpl;
import com.company.gum.entity.Order;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private static OrderServiceImpl instance;
	private OrderDao orderDao = OrderDaoImpl.getInstance();

	private OrderServiceImpl() {
	}

	public static OrderServiceImpl getInstance() {
		if (instance == null) {
			instance = new OrderServiceImpl();
		}
		return instance;
	}

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

	@Override
	public List<Order> findOrderWithFilter(Order filter) throws ServiceException {
		return null;
	}

	@Override
	public List<Order> findAllActiveOrder() throws ServiceException {
		return null;
	}

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
