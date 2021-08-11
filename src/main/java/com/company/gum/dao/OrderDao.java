package com.company.gum.dao;

import com.company.gum.entity.Order;

import java.util.List;

public interface OrderDao {

    Order createOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(int orderId);

    boolean findOrder(int orderId);

    List<Order> findAllOrder();

    List<Order> findOrderWithFilter(Order filter);

    List<Order> findAllActiveOrder();

    List<Order> findActiveOrderByTrainer(int trainerId);

    List<Order> findActiveOrderByClient(int clientId);

}
