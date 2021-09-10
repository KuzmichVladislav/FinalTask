package com.company.gum.dao.impl;

import com.company.gum.dao.ClientDao;
import com.company.gum.entity.Client;
import com.company.gum.entity.User;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.gum.dao.TableColumnName.*;

public class ClientDaoImpl implements ClientDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_CREATE_USER = "INSERT INTO users(login, password, name, surname, mail)\n"
            + "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_CREATE_CLIENT = "INSERT INTO clients(client_id, phone_number)\n"
            + "VALUES (?, ?)";
    private static final String SQL_EDIT_CLIENT = "UPDATE clients, users\n"
            + "SET name  = IFNULL(?, name),\n"
            + "    surname      = IFNULL(?, surname),\n"
            + "    phone_number      = IFNULL(?, phone_number),\n"
            + "    mail = IFNULL(?, mail)\n"
            + "WHERE user_id = ?\n";
    private static final String SQL_VERIFICATION = "UPDATE users\n"
            + "SET is_active   = true,\n"
            + "    is_verified = true\n"
            + "WHERE user_id = ?\n"
            + "  AND is_verified = false";
    private static final String SQL_REFILL_MONEY = "UPDATE clients\n"
            + "SET money = money + ?\n"
            + "WHERE client_id = ?";
    private static final String SQL_WITHDRAW_MONEY = "UPDATE clients\n"
            + "SET money = money + ?\n"
            + "WHERE client_id = ?";
    private static final String SQL_FIND_CLIENT_BY_ID = "SELECT client_id,\n"
            + "       register_date,\n"
            + "       phone_number,\n"
            + "       discount,\n"
            + "       discount_type,\n"
            + "       money,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
            + "       mail,\n"
            + "       is_verified\n,"
            + "       image\n"
            + "FROM clients,\n"
            + "     users\n"
            + "WHERE client_id = ?\n"
            + "AND role = 'CLIENT'";
    private static final String SQL_FIND_ALL_CLIENT = "SELECT client_id,\n"
            + "       register_date,\n"
            + "       phone_number,\n"
            + "       discount,\n"
            + "       discount_type,\n"
            + "       money,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
            + "       mail,\n"
            + "       is_verified\n"
            + "FROM clients,\n"
            + "     users\n"
            + "WHERE role = 'CLIENT'";
    private static final String SQL_FIND_ALL_ACTIVE_CLIENT = "SELECT client_id,\n"
            + "       register_date,\n"
            + "       phone_number,\n"
            + "       discount,\n"
            + "       discount_type,\n"
            + "       money,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
            + "       mail,\n"
            + "       is_verified\n"
            + "FROM clients,\n"
            + "     users\n"
            + "WHERE is_active = true\n"
            + "AND role = 'CLIENT'";
    private static final String SQL_FIND_ALL_CLIENT_BY_ANTHROPONYM = "SELECT client_id,\n"
            + "       register_date,\n"
            + "       phone_number,\n"
            + "       discount,\n"
            + "       discount_type,\n"
            + "       money,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
            + "       mail,\n"
            + "       is_verified\n"
            + "FROM clients,\n"
            + "     users\n"
            + "WHERE name = IFNULL(?, name)\n"
            + "  AND surname = IFNULL(?, surname)\n"
            + "AND role = 'CLIENT'";

    private static ClientDaoImpl mInstance;

    private ClientDaoImpl() {
    }

    public static ClientDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new ClientDaoImpl();
        }
        return mInstance;
    }

    @Override
    public Client createClient(Client client) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement clientStatement = connection.prepareStatement(SQL_CREATE_CLIENT)) {
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
                logger.debug("Client {} was created", client);
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
    public boolean editClient(Client client) throws DaoException {
        boolean isUpdated;
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

            isUpdated = statement.executeUpdate() == 1;

            logger.debug(isUpdated ? "Client " + client.getId() + " was updated" : "Client " + client.getId() + " was not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean verification(int clientId) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_VERIFICATION)) {
            statement.setInt(1, clientId);

            isUpdated = statement.executeUpdate() == 1;

            logger.debug(isUpdated ? "Client with id " + clientId + " was verified" : "Client id " + clientId + " verification failed");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

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

    @Override
    public Client findClientById(int clientId) throws DaoException {
        Client client = new Client();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement clientStatement = connection.prepareStatement(SQL_FIND_CLIENT_BY_ID)) {

            clientStatement.setInt(1, clientId);

            ResultSet resultClientSet = clientStatement.executeQuery();
            if (resultClientSet.next()) {
                client = getClientFromResultSet(resultClientSet);
                logger.debug("Client with id \"{}\" was found:\n{}", clientId, client);
            } else {
                logger.debug("Client with id \"{}\" was not found:\n{}", clientId, client);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public List<Client> findAllClient() throws DaoException {
        List<Client> resultArray = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_CLIENT)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                resultArray.add(client);
            }

            logger.debug(resultArray.isEmpty() ? "No clients found" : "Found {} clients:\n{}", resultArray.size(), resultArray);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultArray;
    }

    @Override
    public List<Client> findAllActiveClient() throws DaoException {
        List<Client> resultArray = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_CLIENT)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                resultArray.add(client);
            }

            logger.debug(resultArray.isEmpty() ? "No active clients found" : "Found {} active clients:\n{}", resultArray.size(), resultArray);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultArray;
    }

    @Override
    public List<Client> findAllClientByAnthroponym(String name, String surname) throws DaoException {
        List<Client> resultArray = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_CLIENT_BY_ANTHROPONYM)) {
            if (name != null) {
                statement.setString(1, name);
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (name != null) {
                statement.setString(2, surname);
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Client client = getClientFromResultSet(resultSet);
                resultArray.add(client);
            }

            logger.debug(resultArray.isEmpty() ? "No client with anthroponym " + name + " " + surname + " found" : "Found " + resultArray.size() + " clients with anthroponym " + name + " " + surname);

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return resultArray;
    }

    private Client getClientFromResultSet(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getInt(CLIENT_ID));
        client.setLogin(resultSet.getString(USER_LOGIN));
        client.setPassword(resultSet.getString(USER_PASSWORD));
        client.setProfileImage(resultSet.getString(PROFILE_IMAGE));
        client.setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
        client.setMail(resultSet.getString(MAIL));
        client.setName(resultSet.getString(USER_NAME));
        client.setSurname(resultSet.getString(USER_SURNAME));
        client.setActive(resultSet.getBoolean(IS_ACTIVE));
        client.setRegisterDate(resultSet.getTimestamp(REGISTER_DATE).toLocalDateTime());
        client.setPhone(resultSet.getString(PHONE_NUMBER));
        client.setDiscount(resultSet.getInt(DISCOUNT));
        client.setDiscountLevel(resultSet.getInt(DISCOUNT_TYPE));
        client.setMoney(resultSet.getBigDecimal(MONEY));
        client.setPhoto(resultSet.getBytes(PHOTO));
        return client;
    }
}
