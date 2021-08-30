package com.company.gum.dao.impl;

import com.company.gum.dao.UserDao;
import com.company.gum.entity.User;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.company.gum.dao.TableColumnName.*;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_USER_BY_ID = "SELECT user_id,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
            + "       mail\n"
            + "FROM users\n"
            + "WHERE user_id = ?";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT user_id,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
            + "       mail\n"
            + "FROM users\n"
            + "WHERE login = ?\n";
    private static final String SQL_UPDATE_USER_PASSWORD = "UPDATE users\n"
            + "SET password = IFNULL(?, password)\n"
            + "WHERE user_id = ?";
    private static final String SQL_UPDATE_USER_IMAGE = "UPDATE users\n"
            + "SET profile_image = IFNULL(?, password)\n"
            + "WHERE user_id = ?";
    private static final String SQL_DELETE_USER = "UPDATE users\n"
            + "SET is_active = false\n"
            + "WHERE user_id = ?";
    private static final String SQL_RESTORE_USER = "UPDATE users\n"
            + "SET is_active = true\n"
            + "WHERE user_id = ?";

    private static UserDaoImpl mInstance;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new UserDaoImpl();
        }
        return mInstance;
    }

    @Override
    public User findUserById(int userId) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                logger.info("User with id \"{}\" was found: {}", user.getId(), user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
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
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
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
    public boolean updateUserImage(User user) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_IMAGE)) {
            statement.setString(1, user.getProfileImage());
            statement.setInt(2, user.getId());

            isUpdated = statement.executeUpdate() == 1;

            if (isUpdated) {
                logger.debug("Profile image path for user with id \"{}\" has been updated", user.getId());
            } else {
                logger.debug("Can't update image path for user with id \"{}\"", user.getId());
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteUser(int userId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
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
    public boolean restoreUser(int userId) throws DaoException {
        boolean isRestored;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_RESTORE_USER)) {
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
        return new User.Builder().id(resultSet.getInt(USER_ID))
                .login(resultSet.getString(USER_LOGIN))
                .password(resultSet.getString(USER_PASSWORD))
                .profileImage(resultSet.getString(PROFILE_IMAGE))
                .role(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()))
                .mail(resultSet.getString(MAIL))
                .name(resultSet.getString(USER_NAME))
                .surname(resultSet.getString(USER_SURNAME))
                .isActive(resultSet.getBoolean(IS_ACTIVE))
                .build();
    }
}
