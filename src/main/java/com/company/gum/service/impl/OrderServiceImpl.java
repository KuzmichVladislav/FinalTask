package com.company.gum.service.impl;

import com.company.gum.dao.OrderDao;
import com.company.gum.dao.impl.OrderDaoImpl;
import com.company.gum.entity.Order;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
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
    public boolean updateOrder(Order order) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) throws ServiceException {
        return false;
    }

    @Override
    public Order findOrder(int orderId) throws ServiceException {
        return null;
    }

    @Override
    public List<Order> findAllOrder() throws ServiceException {
        return null;
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
        return null;
    }

    @Override
    public List<Order> findActiveOrderByClient(int clientId) throws ServiceException {
        List<Order> orders;
        try {
            orders=orderDao.findActiveOrderByClient(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }
}
