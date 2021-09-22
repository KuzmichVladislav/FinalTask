package com.company.gum.dao;

import com.company.gum.entity.Order;
import com.company.gum.exception.DaoException;

import java.util.List;

public interface OrderDao {

    Order createOrder(Order order) throws DaoException;

    boolean deleteOrder(int orderId) throws DaoException;

    Order findOrder(int orderId) throws DaoException;

    List<Order> findAllOrder() throws DaoException;

    List<Order> findOrderWithFilter(Order filter) throws DaoException;

    List<Order> findAllActiveOrder() throws DaoException;

    List<Order> findActiveOrderByTrainer(int trainerId) throws DaoException;

    List<Order> findActiveOrderByClient(int clientId) throws DaoException;

    boolean editNutrition(int orderId, String nutrition) throws DaoException;

    boolean editExercises(int orderId, String exercises) throws DaoException;

    boolean editOrderStatus(int orderId, Order.OrderStatus orderStatus) throws DaoException;
}
