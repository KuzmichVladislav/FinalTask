package com.company.gum.dao.impl;

import com.company.gum.dao.ClientDao;
import com.company.gum.entity.user_impl.Client;
import com.company.gum.entity.user_impl.User;
import com.company.gum.entity.user_impl.UserRole;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

import static com.company.gum.dao.TableColumnNames.*;

public class ClientDaoImpl implements ClientDao {

    private static final Logger logger = LogManager.getLogger();

    private static ClientDao clientDao = new ClientDaoImpl();

    private ClientDaoImpl() {
    }


    public static ClientDao getInstance() {
        return clientDao;
    }

    @Override
    public Client create(Client client) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement("INSERT INTO users(login, password, name, surname, mail) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement clientStatement = connection.prepareStatement("INSERT INTO clients(client_id, phone_number) VALUES (?, ?)")) {
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
                logger.debug("Client created = {}", client);
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

    @Override
    public boolean update(Client client) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE clients SET phone_number = IFNULL(?, phone_number), discount = IFNULL(?, discount), discount_type = IFNULL(?, discount_type) WHERE client_id = ?")) {
            if (client.getName() != null) {
                statement.setString(1, client.getPhone());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (client.getName() != null) {
                statement.setInt(2, client.getDiscount());
            } else {
                statement.setNull(2, Types.INTEGER);
            }
            if (client.getName() != null) {
                statement.setInt(3, client.getDiscountLevel());
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            statement.setInt(4, client.getId());

            isUpdated = statement.executeUpdate() == 1;

            logger.debug("Client updated, new client - {}", client);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }


    @Override
    public boolean verification(int clientId) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET is_active = true, is_verified = true WHERE user_id = ? AND is_verified = false")) {
            statement.setInt(1, clientId);

            isUpdated = statement.executeUpdate() == 1;
            logger.debug("Client verification id - {}, result - {}", clientId, isUpdated);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean refill(int clientId, BigDecimal amount) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement updateClientStatement = connection.prepareStatement("UPDATE clients SET money = money + ? WHERE client_id = ?")) {
            try {
                connection.setAutoCommit(false);
                updateClientStatement.setBigDecimal(1, amount);
                updateClientStatement.setInt(2, clientId);
                isUpdated = updateClientStatement.executeUpdate() == 1;

                if (!isUpdated) {
                    connection.rollback();
                }

                connection.commit();
                logger.debug("Client cash updated, deposit sum - {}", amount.doubleValue());
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

    @Override
    public boolean withdrawal(int clientId, BigDecimal amount) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement updateClientStatement = connection.prepareStatement("UPDATE clients SET money = money - ? WHERE client_id = ?")) {

            try {
                connection.setAutoCommit(false);

                updateClientStatement.setBigDecimal(1, amount.negate());
                updateClientStatement.setInt(2, clientId);
                isUpdated = updateClientStatement.executeUpdate() == 1;

                if (!isUpdated) {
                    connection.rollback();
                }

                connection.commit();
                logger.debug("Client cash updated, deposit sum - {}", amount.doubleValue());
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

    @Override
    public Client findClientById(int clientId) throws DaoException {
        Client client = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement("SELECT login, password, name, surname, mail FROM users where user_id = ?");
             PreparedStatement clientStatement = connection.prepareStatement("SELECT client_id, register_date, phone_number, discount, discount_type, money FROM clients WHERE client_id = ?")) {//SELECT clientId, name, lastName, registerDate, discount, phone, cash, discountLevel, active, mail FROM clients WHERE clientId = ?
            userStatement.setInt(1, clientId);
            clientStatement.setInt(1, clientId);

            ResultSet resultUserSet = clientStatement.executeQuery();
            ResultSet resultClientSet = clientStatement.executeQuery();

            if (resultClientSet.next()) {
                client = getClientFromResultSet(resultClientSet);
                logger.debug("Find, client - {}", client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    private Client getClientFromResultSet(ResultSet resultClientSet) throws SQLException {
        Client client = new Client();
        client.setId(resultClientSet.getInt(CLIENT_ID));
        client.setRegisterDate(resultClientSet.getTimestamp(REGISTER_DATE).toLocalDateTime());
        client.setPhone(resultClientSet.getString(PHONE_NUMBER));
        client.setDiscount(resultClientSet.getInt(DISCOUNT));
        client.setDiscountLevel(resultClientSet.getInt(DISCOUNT_TYPE));
        client.setMoney(resultClientSet.getBigDecimal(MONEY));
        return client;
    }

    private User getUserFromResultSet(ResultSet resultUserSet) throws SQLException {
        User user = new User();
        user.setId(resultUserSet.getInt(USERS_ID));
        user.setLogin(resultUserSet.getString(USERS_LOGIN));
        user.setPassword(resultUserSet.getString(USERS_PASSWORD));
        user.setProfileImage(resultUserSet.getString(PROFILE_IMAGE));
        user.setRole(UserRole.valueOf(resultUserSet.getString(USERS_ROLE).toUpperCase()));
        user.setMail(resultUserSet.getString(MAIL));
        user.setName(resultUserSet.getString(USERS_NAME));
        user.setSurname(resultUserSet.getString(USERS_SURNAME));
        user.setActive(resultUserSet.getBoolean(IS_ACTIVE));
        return user;
    }

    @Override
    public List<Client> findAllClient() {
        return null;
    }

    @Override
    public List<Client> findAllActiveClient() {
        return null;
    }

    @Override
    public List<Client> findAllActiveClientByName(String name, String surname) {
        return null;
    }
}
