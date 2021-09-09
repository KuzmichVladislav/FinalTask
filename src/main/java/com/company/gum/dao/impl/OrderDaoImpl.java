package com.company.gum.dao.impl;

import com.company.gum.dao.OrderDao;
import com.company.gum.entity.Order;
import com.company.gum.entity.OrderStatus;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.company.gum.dao.TableColumnName.*;

public class OrderDaoImpl implements OrderDao {

	private static final Logger logger = LogManager.getLogger();

	private static final String SQL_CREATE_ORDER = "INSERT INTO orders(client_id, trainer_id, client_comment, start_order_date, end_order_date, price)\n"
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_QUERY = "UPDATE orders\n"
			+ "SET client_id        = IFNULL(?, client_id),\n"
			+ "    trainer_id       = IFNULL(?, trainer_id),\n"
			+ "    exercises        = IFNULL(?, exercises),\n"
			+ "    nutrition        = IFNULL(?, nutrition),\n"
			+ "    start_order_date = IFNULL(?, start_order_date),\n"
			+ "    end_order_date   = IFNULL(?, end_order_date),\n"
			+ "    price            = IFNULL(?, price),\n"
			+ "    client_comment   = IFNULL(?, client_comment),\n"
			+ "    order_status     = IFNULL(?, order_status),\n"
			+ "    is_active        = IFNULL(?, is_active)\n"
			+ "WHERE order_id = ?";
	private static final String SQL_DELETE_ORDER = "UPDATE orders\n"
			+ "SET is_active = false\n"
			+ "WHERE order_id = ?";
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
			+ "         LEFT JOIN users u1 on o.client_id = u1.user_id\n"
			+ "         LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
			+ "WHERE o.order_id = ?";

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
			+ "         LEFT JOIN users u1 on o.client_id = u1.user_id\n"
			+ "         LEFT JOIN users u2 on o.trainer_id = u2.user_id";

	private static final String SQL_FIND_ALL_ORDER_WITH_FILTER = "SELECT o.order_id,\n"
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
			+ "         LEFT JOIN users u1 on o.client_id = u1.user_id\n"
			+ "         LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
			+ "WHERE u1.user_id = IFNULL(?, u1.user_id)\n"
			+ "  AND u1.name = IFNULL(?, u1.name)\n"
			+ "  AND u1.surname = IFNULL(?, u1.surname)\n"
			+ "  AND u2.user_id = IFNULL(?, u2.user_id)\n"
			+ "  AND u2.name = IFNULL(?, u2.name)\n"
			+ "  AND u2.surname = IFNULL(?, u2.surname)\n"
			+ "  AND start_order_date >= IFNULL(?, start_order_date)\n"
			+ "  AND end_order_date <= IFNULL(?, end_order_date)\n"
			+ "  AND price = IFNULL(?, price)\n"
			+ "  AND order_status = IFNULL(?, order_status)\n"
			+ "  AND o.is_active = IFNULL(?, o.is_active)";

	private static final String SQL_FIND_ALL_ACTIVE_ORDER = "SELECT o.order_id,\n"
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
			+ "         LEFT JOIN users u1 on o.client_id = u1.user_id\n"
			+ "         LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
			+ "WHERE o.is_active = true";

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
			+ "         LEFT JOIN users u1 on o.client_id = u1.user_id\n"
			+ "         LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
			+ "WHERE o.is_active = true\n"
			+ "  AND o.trainer_id = ?";

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
			+ "       o.is_active\n"
			+ "FROM orders o\n"
			+ "         LEFT JOIN users u1 on o.client_id = u1.user_id\n"
			+ "         LEFT JOIN users u2 on o.trainer_id = u2.user_id\n"
			+ "WHERE o.is_active = true\n"
			+ "  AND o.client_id = ?";
	private static OrderDaoImpl mInstance;

	private OrderDaoImpl() {
	}

	public static OrderDaoImpl getInstance() {
		if (mInstance == null) {
			mInstance = new OrderDaoImpl();
		}
		return mInstance;
	}

	@Override
	public Order createOrder(Order order) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER, Statement.RETURN_GENERATED_KEYS)) {
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
			logger.debug("Order {} was created", order);

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return order;
	}

	@Override
	public boolean updateOrder(Order order) throws DaoException {
		boolean isUpdated;

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_QUERY)) {
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
			logger.debug("Order was {} updated", order);

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isUpdated;

	}

	@Override
	public boolean deleteOrder(int orderId) throws DaoException {
		boolean isDeleted;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)) {
			statement.setInt(1, orderId);
			isDeleted = statement.execute();

			if (isDeleted) {
				logger.debug("Order with id \"{}\" has been deleted", orderId);
			} else {
				logger.debug("Can't delete order with id \"{}\"", orderId);
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
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER)) {
			statement.setInt(1, orderId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				order = getOrderFromResultSet(resultSet);
				logger.debug("Order with id \"{}\" was found: {}", order.getId(), order);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return order;
	}

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
			logger.debug("Found {} orders: {}", result.size(), result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public List<Order> findOrderWithFilter(Order filter) throws DaoException {

		List<Order> result = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER_WITH_FILTER)) {
			if (filter.getClientId() != null) {
				statement.setInt(1, filter.getClientId());
			} else {
				statement.setNull(1, Types.INTEGER);
			}
			if (filter.getClientName() != null) {
				statement.setString(2, filter.getClientName());
			} else {
				statement.setNull(2, Types.VARCHAR);
			}
			if (filter.getClientSurname() != null) {
				statement.setString(3, filter.getClientSurname());
			} else {
				statement.setNull(3, Types.VARCHAR);
			}
			if (filter.getTrainerId() != null) {
				statement.setInt(4, filter.getTrainerId());
			} else {
				statement.setNull(4, Types.INTEGER);
			}
			if (filter.getTrainerName() != null) {
				statement.setString(5, filter.getTrainerName());
			} else {
				statement.setNull(5, Types.VARCHAR);
			}
			if (filter.getTrainerSurname() != null) {
				statement.setString(6, filter.getTrainerSurname());
			} else {
				statement.setNull(6, Types.VARCHAR);
			}
			if (filter.getStartDate() != null) {
				statement.setDate(7, Date.valueOf(filter.getStartDate()));
			} else {
				statement.setNull(7, Types.DATE);
			}
			if (filter.getEndDate() != null) {
				statement.setDate(8, Date.valueOf(filter.getEndDate()));
			} else {
				statement.setNull(8, Types.DATE);
			}
			if (filter.getPrice() != null) {
				statement.setBigDecimal(9, filter.getPrice());
			} else {
				statement.setNull(9, Types.DECIMAL);
			}
			if (filter.getOrderStatus() != null) {
				statement.setInt(10, filter.getOrderStatus().ordinal());
			} else {
				statement.setNull(10, Types.INTEGER);
			}
			if (filter.getActive() != null) {
				statement.setBoolean(11, filter.getActive());
			} else {
				statement.setNull(11, Types.BOOLEAN);
			}

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Order order = getOrderFromResultSet(resultSet);
				result.add(order);
			}

			logger.debug("Found {} orders with filter: {}", result.size(), result);

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	@Override
	public List<Order> findAllActiveOrder() throws DaoException {
		List<Order> result = new ArrayList<>();

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_ORDER)) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Order order = getOrderFromResultSet(resultSet);
				result.add(order);
			}
			logger.debug("Found {} active orders: {}", result.size(), result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

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
			logger.debug("Trainer with id \"{}\" found {} active orders: {}", trainerId, result.size(), result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

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
			logger.debug("Client with id \"{}\" found {} orders: {}", clientId, result.size(), result);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
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
		order.setActive(resultSet.getBoolean(IS_ACTIVE));
		return order;
	}
}
