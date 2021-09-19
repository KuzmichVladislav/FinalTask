package com.company.gum.service;

import com.company.gum.entity.Order;
import com.company.gum.exception.ServiceException;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order) throws ServiceException;

    boolean updateOrder(Order order) throws ServiceException;

    boolean deleteOrder(int orderId) throws ServiceException;

    Order findOrder(int orderId) throws ServiceException;

    List<Order> findAllOrder() throws ServiceException;

    List<Order> findOrderWithFilter(Order filter) throws ServiceException;

    List<Order> findAllActiveOrder() throws ServiceException;

    List<Order> findActiveOrderByTrainer(int trainerId) throws ServiceException;

    List<Order> findActiveOrderByClient(int clientId) throws ServiceException;

}
