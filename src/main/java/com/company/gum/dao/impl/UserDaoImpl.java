package com.company.gum.dao.impl;

import com.company.gum.dao.ColumnTableName;
import com.company.gum.dao.UserDao;
import com.company.gum.entity.userImpl.User;
import com.company.gum.entity.userImpl.UserRole;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static volatile UserDaoImpl mInstance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (mInstance == null) {
            synchronized (UserDaoImpl.class) {
                if (mInstance == null) {
                    mInstance = new UserDaoImpl();
                }
            }
        }
        return mInstance;
    }


/*    private static UserDao userDao = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return userDao;
    }*/


    @Override
    public User findById(int userId) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT user_id, login, password, role, name, surname, is_active FROM users WHERE user_id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                logger.info("User with id \"{}\" was found", user.getId());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }


    @Override
    public User findByLogin(String login) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT user_id, login, password, role, name, surname, is_active FROM users WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                logger.info("User with login \"{}\" was found", user.getLogin());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public boolean updateUserPassword(User user) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = IFNULL(?, password) WHERE user_id = ?")) {
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getId());

            isUpdated = statement.executeUpdate() == 1;

            if (isUpdated) {
                logger.debug("Password for user with id \"{}\" has been updated", user.getId());
            } else {
                logger.debug("Can't update password for user with id \"{}\"", user.getId());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserImage(User user) {
        return false;
    }

    @Override
    public boolean delete(int userId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET is_active = false WHERE user_id = ?")) {
            statement.setInt(1, userId);

            isDeleted = statement.executeUpdate() == 1;

            if (isDeleted) {
                logger.debug("User with id \"{}\" has been deleted", userId);
            } else {
                logger.debug("Can't delete user with id \"{}\"", userId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean restore(int userId) throws DaoException {
        boolean isRestored;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET is_active = true WHERE user_id = ?")) {
            statement.setInt(1, userId);

            isRestored = statement.executeUpdate() == 1;

            if (isRestored) {
                logger.debug("User with id \"{}\" has been restored", userId);
            } else {
                logger.debug("Can't restore user with id \"{}\"", userId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRestored;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ColumnTableName.USERS_ID.getColumnName()));
        user.setLogin(resultSet.getString(ColumnTableName.USERS_LOGIN.getColumnName()));
        user.setPassword(resultSet.getString(ColumnTableName.USERS_PASSWORD.getColumnName()));
        //user.setImagePath(resultSet.getString(ColumnTableName.USERS_PROFILE_IMAGE_PATH.getColumnName()));
        user.setRole(UserRole.valueOf(resultSet.getString(ColumnTableName.USERS_ROLE.getColumnName()).toUpperCase()));
        user.setName(resultSet.getString(ColumnTableName.USERS_NAME.getColumnName()));
        user.setSurname(resultSet.getString(ColumnTableName.USERS_SURNAME.getColumnName()));
        user.setActive(resultSet.getBoolean(ColumnTableName.IS_ACTIVE.getColumnName()));
        return user;
    }
}

