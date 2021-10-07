package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.dao.ClientDao;
import com.company.gum.model.entity.Client;
import com.company.gum.model.entity.User;
import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Base64;

import static com.company.gum.model.dao.TableColumnName.*;

/**
 * The Class ClientDaoImpl.
 *
 * @author Vladislav Kuzmich
 */
public class ClientDaoImpl implements ClientDao {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The Constant SQL_CREATE_USER.
     */
    private static final String SQL_CREATE_USER = "INSERT INTO users(login, password, name, surname, mail)\n"
            + "VALUES (?, ?, ?, ?, ?)";

    /**
     * The Constant SQL_CREATE_CLIENT.
     */
    private static final String SQL_CREATE_CLIENT = "INSERT INTO clients(client_id, phone_number)\n"
            + "VALUES (?, ?)";

    /**
     * The Constant SQL_EDIT_CLIENT.
     */
    private static final String SQL_EDIT_CLIENT = "UPDATE clients, users\n"
            + "SET name = IFNULL(?, name),\n"
            + "    surname = IFNULL(?, surname),\n"
            + "    phone_number = IFNULL(?, phone_number),\n"
            + "    mail = IFNULL(?, mail)\n"
            + "WHERE user_id = ?\n";

    /**
     * The Constant SQL_VERIFICATION.
     */
    private static final String SQL_VERIFICATION = "UPDATE users\n"
            + "SET is_active   = true,\n"
            + "    is_verified = true\n"
            + "WHERE user_id = ?\n"
            + "  AND is_verified = false";

    /**
     * The Constant SQL_REFILL_MONEY.
     */
    private static final String SQL_REFILL_MONEY = "UPDATE clients\n"
            + "SET money = money "
            + "+ ?\n"
            + "WHERE client_id = ?";

    /**
     * The Constant SQL_WITHDRAW_MONEY.
     */
    private static final String SQL_WITHDRAW_MONEY = "UPDATE clients\n"
            + "SET money = money "
            + "+ ?\n"
            + "WHERE client_id = ?";

    /**
     * The Constant SQL_FIND_CLIENT_BY_ID.
     */
    private static final String SQL_FIND_CLIENT_BY_ID = "SELECT client_id,\n"
            + "       register_date,\n"
            + "       phone_number,\n"
            + "       discount,\n"
            + "       money,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       mail,\n"
            + "       is_verified,\n"
            + "       image\n"
            + "FROM users\n"
            + "LEFT JOIN clients on users.user_id = client_id\n"
            + "WHERE client_id = ?\n"
            + "  AND role = 'CLIENT'";

    /**
     * The Constant SQL_ASSIGN_DISCOUNT.
     */
    private static final String SQL_ASSIGN_DISCOUNT = "UPDATE clients\n"
            + "SET discount = ?\n"
            + "WHERE client_id = ?";

    /**
     * The instance.
     */
    private static ClientDaoImpl instance;

    /**
     * Instantiates a new client dao impl.
     */
    private ClientDaoImpl() {
    }

    /**
     * Gets the single instance of ClientDaoImpl.
     *
     * @return single instance of ClientDaoImpl
     */
    public static ClientDaoImpl getInstance() {
        if (instance == null) {
            instance = new ClientDaoImpl();
        }
        return instance;
    }

    /**
     * Creates the client.
     *
     * @param client the client
     * @return the client
     * @throws DaoException the dao exception
     */
    @Override
    public Client createClient(Client client) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement(SQL_CREATE_USER,
                     Statement.RETURN_GENERATED_KEYS); PreparedStatement clientStatement =
                     connection.prepareStatement(SQL_CREATE_CLIENT)) {
            try {
                connection.setAutoCommit(false);
                userStatement.setString(1, client.getLogin());
                userStatement.setString(2, client.getPassword());
                userStatement.setString(3, client.getName());
                userStatement.setString(4, client.getSurname());
                userStatement.setString(5, client.getMail());

                userStatement.execute();

                ResultSet resultSet = userStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int clientId = resultSet.getInt(1);
                    client.setId(clientId);
                }
                clientStatement.setInt(1, client.getId());
                if (client.getPhone() != null) {
                    clientStatement.setString(2, client.getPhone());
                } else {
                    clientStatement.setNull(2, Types.NULL);
                }
                clientStatement.execute();

                connection.commit();
                logger.debug("Client with id {} was created", client.getId());
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    /**
     * Edits the client.
     *
     * @param client the client
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean editClient(Client client) throws DaoException {
        boolean isEdited;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_CLIENT)) {
            if (client.getName() != null) {
                statement.setString(1, client.getName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (client.getSurname() != null) {
                statement.setString(2, client.getSurname());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (client.getPhone() != null) {
                statement.setString(3, client.getPhone());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            if (client.getMail() != null) {
                statement.setString(4, client.getMail());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }
            statement.setInt(5, client.getId());

            isEdited = statement.executeUpdate() == 1;

            logger.debug(isEdited ? "Client " + client.getId() + " was updated"
                    : "Client " + client.getId() + " was not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

    /**
     * Verification.
     *
     * @param clientId the client id
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean verification(int clientId) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_VERIFICATION)) {
            statement.setInt(1, clientId);

            isUpdated = statement.executeUpdate() == 1;

            logger.debug(isUpdated ? "Client with id {} was verified"
                    : "Client id {} verification failed", clientId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    /**
     * Refill money.
     *
     * @param clientId the client id
     * @param amount   the amount
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean refillMoney(int clientId, BigDecimal amount) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement updateClientStatement = connection.prepareStatement(SQL_REFILL_MONEY)) {
            try {
                connection.setAutoCommit(false);
                updateClientStatement.setBigDecimal(1, amount);
                updateClientStatement.setInt(2, clientId);
                isUpdated = updateClientStatement.executeUpdate() == 1;

                if (!isUpdated) {
                    connection.rollback();
                }
                connection.commit();
                logger.debug("Client's money with \"{}\" was replenished by {}", clientId, amount.doubleValue());
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    /**
     * Withdraw money.
     *
     * @param clientId the client id
     * @param amount   the amount
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean withdrawMoney(int clientId, BigDecimal amount) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement updateClientStatement = connection.prepareStatement(SQL_WITHDRAW_MONEY)) {
            try {
                connection.setAutoCommit(false);

                updateClientStatement.setBigDecimal(1, amount.negate());
                updateClientStatement.setInt(2, clientId);
                isUpdated = updateClientStatement.executeUpdate() == 1;

                if (!isUpdated) {
                    connection.rollback();
                }

                connection.commit();
                logger.debug("Client's money with \"{}\" was withdrawn by {}", clientId, amount.doubleValue());
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    /**
     * Assign discount.
     *
     * @param clientId the client id
     * @param discount the discount
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean assignDiscount(int clientId, BigDecimal discount) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement updateClientStatement = connection.prepareStatement(SQL_ASSIGN_DISCOUNT)) {
            try {
                connection.setAutoCommit(false);
                updateClientStatement.setBigDecimal(1, discount);
                updateClientStatement.setInt(2, clientId);
                isUpdated = updateClientStatement.executeUpdate() == 1;

                if (!isUpdated) {
                    connection.rollback();
                }
                connection.commit();
                logger.debug("Client's discount with \"{}\" was edit by {}", clientId, discount.doubleValue());
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    /**
     * Find client by id.
     *
     * @param clientId the client id
     * @return the client
     * @throws DaoException the dao exception
     */
    @Override
    public Client findClientById(int clientId) throws DaoException {
        Client client = new Client();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement clientStatement = connection.prepareStatement(SQL_FIND_CLIENT_BY_ID)) {

            clientStatement.setInt(1, clientId);

            ResultSet resultClientSet = clientStatement.executeQuery();
            if (resultClientSet.next()) {
                client = getClientFromResultSet(resultClientSet);
                logger.debug("Client with id \"{}\" was found", clientId);
            } else {
                logger.debug("Client with id \"{}\" was not found", clientId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    /**
     * Gets the client from result set.
     *
     * @param resultSet the result set
     * @return the client from result set
     * @throws SQLException the SQL exception
     */
    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
        return new Client.Builder()
                .id(resultSet.getInt(CLIENT_ID))
                .login(resultSet.getString(USER_LOGIN))
                .password(resultSet.getString(USER_PASSWORD))
                .role(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()))
                .mail(resultSet.getString(MAIL))
                .name(resultSet.getString(USER_NAME))
                .surname(resultSet.getString(USER_SURNAME))
                .isActive(resultSet.getBoolean(IS_ACTIVE))
                .registerDate(resultSet.getTimestamp(REGISTER_DATE).toLocalDateTime())
                .phone(resultSet.getString(PHONE_NUMBER))
                .discount(resultSet.getInt(DISCOUNT))
                .photo(resultSet.getBytes(PHOTO))
                .verification(resultSet.getBoolean(VERIFICATION))
                .money(resultSet.getBigDecimal(MONEY))
                .base64Image(resultSet.getBytes(PHOTO) != null
                        ? IMAGE_SRC_PREFIX + Base64.getEncoder().encodeToString(resultSet.getBytes(PHOTO))
                        : DEFAULT_IMAGE)
                .build();
    }
}
