package com.company.gum.dao.impl;

import com.company.gum.dao.OrderDao;
import com.company.gum.entity.Order;
import com.company.gum.entity.OrderStatus;
import com.company.gum.entity.user_impl.UserRole;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Objects;

import static com.company.gum.dao.TableColumnNames.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String INSERT_QUERY = "INSERT INTO orders(client_id, trainer_id, client_comment, start_order_date, end_order_date, price)\n" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orders\n" +
            "SET client_id        = IFNULL(?, client_id),\n" +
            "    trainer_id       = IFNULL(?, trainer_id),\n" +
            "    exercises        = IFNULL(?, exercises),\n" +
            "    nutrition        = IFNULL(?, nutrition),\n" +
            "    start_order_date = IFNULL(?, start_order_date),\n" +
            "    end_order_date   = IFNULL(?, end_order_date),\n" +
            "    price            = IFNULL(?, price),\n" +
            "    client_comment   = IFNULL(?, client_comment),\n" +
            "    order_status     = IFNULL(?, order_status),\n" +
            "    is_active        = IFNULL(?, is_active)\n" +
            "WHERE order_id = ?";
    private static final String DELETE_QUERY = "UPDATE orders\n" +
            "SET is_active = false\n" +
            "WHERE order_id = ?";
    private static final String FIND_QUERY = "SELECT o.order_id,\n" +
            "       o.client_id,\n" +
            "       (SELECT u1.name where u1.user_id = o.client_id) AS client_name,\n" +
            "       (SELECT u1.surname where u1.user_id = o.client_id) AS client_surname,\n" +
            "       o.trainer_id,\n" +
            "       (SELECT u2.name where u2.user_id = o.trainer_id) as trainer_name,\n" +
            "       (SELECT u2.surname where u2.user_id = o.trainer_id) AS trainer_surname,\n" +
            "       o.register_date,\n" +
            "       o.exercises,\n" +
            "       o.nutrition,\n" +
            "       o.start_order_date,\n" +
            "       o.end_order_date,\n" +
            "       o.price,\n" +
            "       o.client_comment,\n" +
            "       o.order_status,\n" +
            "       o.is_active\n" +
            "FROM orders o\n" +
            "         LEFT JOIN users u1 on o.client_id = u1.user_id\n" +
            "         LEFT JOIN users u2 on o.trainer_id = u2.user_id\n" +
            "WHERE o.order_id = ?";

    private static OrderDao orderDao = new OrderDaoImpl();

    private OrderDaoImpl() {
    }

    public static OrderDao getInstance() {
        return orderDao;
    }

    @Override
    public Order createOrder(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getTrainerId());
            statement.setString(3, order.getClientComment());
            statement.setDate(4, Date.valueOf(order.getStartDate()));
            statement.setDate(5, Date.valueOf(order.getEndDate()));
            statement.setBigDecimal(6, order.getPrice());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                order.setId(orderId);
            }
            logger.debug("Order created = {}", order);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public boolean updateOrder(Order order) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            if (order.getClientId() != null) {
                statement.setInt(1, order.getClientId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if (order.getTrainerId() != null) {
                statement.setInt(2, order.getTrainerId());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            if (order.getExercises() != null) {
                statement.setString(3, order.getExercises());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (order.getNutrition() != null) {
                statement.setString(4, order.getNutrition());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }
            if (order.getStartDate() != null) {
                statement.setDate(5, Date.valueOf(order.getStartDate()));
            } else {
                statement.setNull(5, Types.DATE);
            }
            if (order.getEndDate() != null) {
                statement.setDate(6, Date.valueOf(order.getEndDate()));
            } else {
                statement.setNull(6, Types.DATE);
            }
            if (order.getPrice() != null) {
                statement.setBigDecimal(7, order.getPrice());
            } else {
                statement.setNull(7, Types.DECIMAL);
            }
            if (order.getClientComment() != null) {
                statement.setString(8, order.getClientComment());
            } else {
                statement.setNull(8, Types.VARCHAR);
            }
            if (order.getOrderStatus() != null) {
                statement.setInt(9, order.getOrderStatus().ordinal());
            } else {
                statement.setNull(9, Types.INTEGER);
            }
            if (order.getActive() != null) {
                statement.setBoolean(10, order.getActive());
            } else {
                statement.setNull(10, Types.INTEGER);
            }
            statement.setInt(11, Objects.requireNonNull(order.getId()));
            isUpdated = statement.executeUpdate() == 1;
            logger.debug("Order updated, new order - {}", order);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;

    }

    @Override
    public boolean deleteOrder(int orderId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, orderId);
            isDeleted = statement.execute();

            if (isDeleted) {
                logger.debug("Order deleted, id - {}", orderId);
            } else {
                logger.debug("Unsuccessful order delete, id - {}", orderId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public Order findOrder(int orderId) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = getOrderFromResultSet(resultSet);
                logger.debug("FindActive order - {}", order);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
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

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Date date;
        order.setId(resultSet.getInt(ORDER_ID));
        order.setClientId(resultSet.getInt(CLIENT_ID));
        order.setClientName(resultSet.getString(CLIENT_NAME));
        order.setClientSurname(resultSet.getString(CLIENT_SURNAME));
        order.setTrainerId(resultSet.getInt(TRAINER_ID));
        order.setTrainerName(resultSet.getString(TRAINER_NAME));
        order.setTrainerSurname(resultSet.getString(TRAINER_SURNAME));
        order.setRegisterDate(resultSet.getTimestamp(ORDER_REGISTER_DATE).toLocalDateTime());
        order.setExercises(resultSet.getString(ORDER_EXERCISES));
        order.setNutrition(resultSet.getString(ORDER_NUTRITION));
        order.setStartDate((date = resultSet.getDate(START_ORDER_DATE)) != null ? date.toLocalDate() : null);
        order.setEndDate((date = resultSet.getDate(END_ORDER_DATE)) != null ? date.toLocalDate() : null);
        order.setPrice(resultSet.getBigDecimal(PRICE));
        order.setClientComment(resultSet.getString(CLIENT_COMMENT));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()));
        order.setActive(resultSet.getBoolean(ORDER_IS_ACTIVE));
        return order;
    }
}
