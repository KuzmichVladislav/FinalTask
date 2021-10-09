package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.dao.OrderDao;
import com.company.gum.model.entity.Order;
import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.gum.model.dao.TableColumnName.*;

/**
 * The Class OrderDaoImpl.
 *
 * @author Vladislav Kuzmich
 */
public class OrderDaoImpl implements OrderDao {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The Constant SQL_CREATE_ORDER.
     */
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders(client_id, trainer_id, client_comment, start_order_date, end_order_date, price, nutrition, exercises)\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * The Constant SQL_DELETE_ORDER.
     */
    private static final String SQL_DELETE_ORDER = "UPDATE orders\n"
            + "SET is_active = false\n"
            + "WHERE order_id = ?";

    /**
     * The Constant SQL_FIND_ORDER.
     */
    private static final String SQL_FIND_ORDER = "SELECT o.order_id,\n"
            + "       o.client_id,\n"
            + "       (SELECT u1.name where u1.user_id = o.client_id) AS client_name,\n"
            + "       (SELECT u1.surname where u1.user_id = o.client_id) AS client_surname,\n"
            + "       o.trainer_id,\n"
            + "       (SELECT u2.name where u2.user_id = o.trainer_id) as trainer_name,\n"
            + "       (SELECT u2.surname where u2.user_id = o.trainer_id) AS trainer_surname,\n"
            + "       o.register_date,\n"
            + "       o.exercises,\n"
            + "       o.nutrition,\n"
            + "       o.start_order_date,\n"
            + "       o.end_order_date,\n"
            + "       o.price,\n"
            + "       o.client_comment,\n"
            + "       o.order_status,\n"
            + "       o.is_active\n"
            + "FROM orders o\n"
            + "       LEFT JOIN users u1 on o.client_id = u1.user_id\n"
            + "       LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
            + "WHERE o.order_id = ?";

    /**
     * The Constant SQL_FIND_ALL_ORDER.
     */
    private static final String SQL_FIND_ALL_ORDER = "SELECT o.order_id,\n"
            + "       o.client_id,\n"
            + "       (SELECT u1.name where u1.user_id = o.client_id)     AS client_name,\n"
            + "       (SELECT u1.surname where u1.user_id = o.client_id)  AS client_surname,\n"
            + "       o.trainer_id,\n"
            + "       (SELECT u2.name where u2.user_id = o.trainer_id)    as trainer_name,\n"
            + "       (SELECT u2.surname where u2.user_id = o.trainer_id) AS trainer_surname,\n"
            + "       o.register_date,\n"
            + "       o.exercises,\n"
            + "       o.nutrition,\n"
            + "       o.start_order_date,\n"
            + "       o.end_order_date,\n"
            + "       o.price,\n"
            + "       o.client_comment,\n"
            + "       o.order_status,\n"
            + "       o.is_active\n"
            + "FROM orders o\n"
            + "       LEFT JOIN users u1 on o.client_id = u1.user_id\n"
            + "       LEFT JOIN users u2 on o.trainer_id = u2.user_id";

    /**
     * The Constant SQL_FIND_ALL_ACTIVE_ORDER_BY_TRAINER.
     */
    private static final String SQL_FIND_ALL_ACTIVE_ORDER_BY_TRAINER = "SELECT o.order_id,\n"
            + "       o.client_id,\n"
            + "       (SELECT u1.name where u1.user_id = o.client_id)     AS client_name,\n"
            + "       (SELECT u1.surname where u1.user_id = o.client_id)  AS client_surname,\n"
            + "       o.trainer_id,\n"
            + "       (SELECT u2.name where u2.user_id = o.trainer_id)    as trainer_name,\n"
            + "       (SELECT u2.surname where u2.user_id = o.trainer_id) AS trainer_surname,\n"
            + "       o.register_date,\n"
            + "       o.exercises,\n"
            + "       o.nutrition,\n"
            + "       o.start_order_date,\n"
            + "       o.end_order_date,\n"
            + "       o.price,\n"
            + "       o.client_comment,\n"
            + "       o.order_status,\n"
            + "       o.is_active\n"
            + "FROM orders o\n"
            + "       LEFT JOIN users u1 on o.client_id = u1.user_id\n"
            + "       LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
            + "WHERE o.is_active = true\n"
            + "  AND o.trainer_id = ?";

    /**
     * The Constant SQL_FIND_ALL_ACTIVE_ORDER_BY_CLIENT.
     */
    private static final String SQL_FIND_ALL_ACTIVE_ORDER_BY_CLIENT = "SELECT o.order_id,\n"
            + "       o.client_id,\n"
            + "       (SELECT u1.name where u1.user_id = o.client_id)     AS client_name,\n"
            + "       (SELECT u1.surname where u1.user_id = o.client_id)  AS client_surname,\n"
            + "       o.trainer_id,\n"
            + "       (SELECT u2.name where u2.user_id = o.trainer_id)    as trainer_name,\n"
            + "       (SELECT u2.surname where u2.user_id = o.trainer_id) AS trainer_surname,\n"
            + "       o.register_date,\n"
            + "       o.exercises,\n"
            + "       o.nutrition,\n"
            + "       o.start_order_date,\n"
            + "       o.end_order_date,\n"
            + "       o.price,\n"
            + "       o.client_comment,\n"
            + "       o.order_status,\n"
            + "       o.is_active\n" + "FROM orders o\n"
            + "         LEFT JOIN users u1 on o.client_id = u1.user_id\n"
            + "         LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
            + "WHERE o.is_active = true\n"
            + "  AND o.client_id = ?";

    /**
     * The Constant SQL_EDIT_NUTRITION.
     */
    private static final String SQL_EDIT_NUTRITION = "UPDATE orders\n"
            + "SET nutrition  = IFNULL(?, nutrition)\n"
            + "WHERE order_id = ?\n";

    /**
     * The Constant SQL_EDIT_EXERCISES.
     */
    private static final String SQL_EDIT_EXERCISES = "UPDATE orders\n"
            + "SET exercises  = IFNULL(?, exercises)\n"
            + "WHERE order_id = ?\n";

    /**
     * The Constant SQL_EDIT_ORDER_STATUS.
     */
    private static final String SQL_EDIT_ORDER_STATUS = "UPDATE orders\n"
            + "SET order_status  = IFNULL(?, order_status)\n"
            + "WHERE order_id = ?\n";

    /**
     * The instance.
     */
    private static OrderDaoImpl instance;

    /**
     * Instantiates a new order dao impl.
     */
    private OrderDaoImpl() {
    }

    /**
     * Gets the single instance of OrderDaoImpl.
     *
     * @return single instance of OrderDaoImpl
     */
    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    /**
     * Creates the order.
     *
     * @param order the order
     * @return the order
     * @throws DaoException the dao exception
     */
    @Override
    public Order createOrder(Order order) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, order.getClientId());
            statement.setInt(2, order.getTrainerId());
            statement.setString(3, order.getClientComment());
            statement.setDate(4, Date.valueOf(order.getStartDate()));
            statement.setDate(5, Date.valueOf(order.getEndDate()));
            statement.setBigDecimal(6, order.getPrice());
            statement.setString(7, MESSAGE);
            statement.setString(8, MESSAGE);
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                order.setId(orderId);
                logger.debug("Order with id {} was created", order.getId());
            } else {
                logger.debug("Order was not created");
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    /**
     * Delete order.
     *
     * @param orderId the order id
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean deleteOrder(int orderId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)) {
            statement.setInt(1, orderId);
            isDeleted = statement.executeUpdate() == 1;
            logger.debug(isDeleted ? "Order with id {} has been deleted"
                    : "Can't delete order with id {}", orderId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    /**
     * Find order.
     *
     * @param orderId the order id
     * @return the order
     * @throws DaoException the dao exception
     */
    @Override
    public Order findOrder(int orderId) throws DaoException {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = getOrderFromResultSet(resultSet);
                logger.debug("Order with id \"{}\" was found", orderId);
            } else {
                logger.debug("Order with id \"{}\" was not found", orderId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    /**
     * Find all order.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    @Override
    public List<Order> findAllOrder() throws DaoException {
        List<Order> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }
            logger.debug("Found {} orders", result.size());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find active order by trainer.
     *
     * @param trainerId the trainer id
     * @return the list
     * @throws DaoException the dao exception
     */
    @Override
    public List<Order> findActiveOrderByTrainer(int trainerId) throws DaoException {
        List<Order> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_ORDER_BY_TRAINER)) {
            statement.setInt(1, trainerId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }
            logger.debug("Trainer with id \"{}\" found {} active orders", trainerId, result.size());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find active order by client.
     *
     * @param clientId the client id
     * @return the list
     * @throws DaoException the dao exception
     */
    @Override
    public List<Order> findActiveOrderByClient(int clientId) throws DaoException {
        List<Order> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_ORDER_BY_CLIENT)) {
            statement.setInt(1, clientId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                result.add(order);
            }
            logger.debug("Client with id \"{}\" found {} orders", clientId, result.size());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Edits the nutrition.
     *
     * @param orderId   the order id
     * @param nutrition the nutrition
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean editNutrition(int orderId, String nutrition) throws DaoException {
        boolean isEdited;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_NUTRITION)) {
            if (nutrition != null) {
                statement.setString(1, nutrition);
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            statement.setInt(2, orderId);

            isEdited = statement.executeUpdate() == 1;

            logger.debug(isEdited ? "Order nutrition {} was updated"
                    : "Order nutrition {} was not updated", orderId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

    /**
     * Edits the exercises.
     *
     * @param orderId   the order id
     * @param exercises the exercises
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean editExercises(int orderId, String exercises) throws DaoException {
        boolean isEdited;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_EXERCISES)) {
            if (exercises != null) {
                statement.setString(1, exercises);
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            statement.setInt(2, orderId);

            isEdited = statement.executeUpdate() == 1;

            logger.debug(isEdited ? "Order exercises {} was updated"
                    : "Order exercises {} was not updated", orderId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

    /**
     * Edits the order status.
     *
     * @param orderId     the order id
     * @param orderStatus the order status
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean editOrderStatus(int orderId, Order.OrderStatus orderStatus) throws DaoException {
        boolean isEdited;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_ORDER_STATUS)) {
            if (orderStatus != null) {
                statement.setInt(1, orderStatus.ordinal() + 1);
            } else {
                statement.setNull(1, Types.INTEGER);
            }

            statement.setInt(2, orderId);

            isEdited = statement.executeUpdate() == 1;

            logger.debug(isEdited ? "Order status {} was updated"
                    : "Order status {} was not updated", orderId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

    /**
     * Gets the order from result set.
     *
     * @param resultSet the result set
     * @return the order from result set
     * @throws SQLException the SQL exception
     */
    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Date date;
        return new Order.Builder()
                .id(resultSet.getInt(ORDER_ID))
                .clientId(resultSet.getInt(CLIENT_ID))
                .clientName(resultSet.getString(CLIENT_NAME))
                .clientSurname(resultSet.getString(CLIENT_SURNAME))
                .trainerId(resultSet.getInt(TRAINER_ID))
                .trainerName(resultSet.getString(TRAINER_NAME))
                .trainerSurname(resultSet.getString(TRAINER_SURNAME))
                .registerDate(resultSet.getTimestamp(ORDER_REGISTER_DATE).toLocalDateTime())
                .exercises(resultSet.getString(ORDER_EXERCISES))
                .nutrition(resultSet.getString(ORDER_NUTRITION))
                .startDate((date = resultSet.getDate(START_ORDER_DATE)) != null ? date.toLocalDate() : null)
                .endDate((date = resultSet.getDate(END_ORDER_DATE)) != null ? date.toLocalDate() : null)
                .price(resultSet.getBigDecimal(PRICE))
                .clientComment(resultSet.getString(CLIENT_COMMENT))
                .orderStatus(Order.OrderStatus.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()))
                .active(resultSet.getBoolean(IS_ACTIVE))
                .build();
    }
}
