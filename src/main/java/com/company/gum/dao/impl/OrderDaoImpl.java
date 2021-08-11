package com.company.gum.dao.impl;

import com.company.gum.dao.OrderDao;
import com.company.gum.entity.Order;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        return false;
    }

    @Override
    public boolean findOrder(int orderId) {
        return false;
    }

    @Override
    public List<Order> findAllOrder() {
        return null;
    }

    @Override
    public List<Order> findOrderWithFilter(Order filter) {
        return null;
    }

    @Override
    public List<Order> findAllActiveOrder() {
        return null;
    }

    @Override
    public List<Order> findActiveOrderByTrainer(int trainerId) {
        return null;
    }

    @Override
    public List<Order> findActiveOrderByClient(int clientId) {
        return null;
    }
}
