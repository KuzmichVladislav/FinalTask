package com.company.gum.dao;

import com.company.gum.entity.Order;
import com.company.gum.exception.DaoException;

import java.util.List;

public interface OrderDao {

    Order createOrder(Order order) throws DaoException;

    boolean updateOrder(Order order) throws DaoException;

    boolean deleteOrder(int orderId) throws DaoException;

    Order findOrder(int orderId) throws DaoException;

    List<Order> findAllOrder();

    List<Order> findOrderWithFilter(Order filter);

    List<Order> findAllActiveOrder();

    List<Order> findActiveOrderByTrainer(int trainerId);

    List<Order> findActiveOrderByClient(int clientId);

}
