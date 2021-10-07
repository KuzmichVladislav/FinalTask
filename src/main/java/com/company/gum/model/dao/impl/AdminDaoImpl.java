package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.dao.AdminDao;
import com.company.gum.model.entity.Admin;
import com.company.gum.model.entity.User;
import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Base64;

import static com.company.gum.model.dao.TableColumnName.*;

/**
 * The Class AdminDaoImpl.
 *
 * @author Vladislav Kuzmich
 */
public class AdminDaoImpl implements AdminDao {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The Constant SQL_FIND_ADMIN_BY_ID.
     */
    private static final String SQL_FIND_ADMIN_BY_ID = "SELECT user_id,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       image,\n"
            + "       is_verified,\n"
            + "       mail\n" + "FROM users\n"
            + "WHERE user_id = ?\n"
            + "  AND role = 'ADMIN'";

    /**
     * The Constant SQL_EDIT_ADMIN.
     */
    private static final String SQL_EDIT_ADMIN = "UPDATE users\n"
            + "SET name  = IFNULL(?, name),\n"
            + "    surname      = IFNULL(?, surname),\n"
            + "    mail = IFNULL(?, mail)\n"
            + "WHERE user_id = ?\n";

    /**
     * The Constant SQL_DELETE_USER.
     */
    private static final String SQL_DELETE_USER = "UPDATE users\n"
            + "SET is_active = false\n"
            + "WHERE user_id = ?";

    /**
     * The Constant SQL_RESTORE_USER.
     */
    private static final String SQL_RESTORE_USER = "UPDATE users\n"
            + "SET is_active = true\n"
            + "WHERE user_id = ?";

    /**
     * The instance.
     */
    private static AdminDaoImpl instance;

    /**
     * Instantiates a new admin dao impl.
     */
    private AdminDaoImpl() {
    }

    /**
     * Gets the single instance of AdminDaoImpl.
     *
     * @return single instance of AdminDaoImpl
     */
    public static AdminDaoImpl getInstance() {
        if (instance == null) {
            instance = new AdminDaoImpl();
        }
        return instance;
    }

    /**
     * Find admin by id.
     *
     * @param adminId the admin id
     * @return the admin
     * @throws DaoException the dao exception
     */
    @Override
    public Admin findAdminById(int adminId) throws DaoException {
        Admin admin = new Admin();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ADMIN_BY_ID)) {
            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                admin = getAdminFromResultSet(resultSet);
                logger.debug("Admin with id \"{}\" was found", admin.getId());
            } else {
                logger.debug("Admin with id \"{}\" was found", admin.getId());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return admin;
    }

    /**
     * Edits the admin.
     *
     * @param admin the admin
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean editAdmin(Admin admin) throws DaoException {
        boolean isEdited;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_ADMIN)) {
            if ((admin.getName() != null)) {
                statement.setString(1, admin.getName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            if (admin.getSurname() != null) {
                statement.setString(2, admin.getSurname());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (admin.getMail() != null) {
                statement.setString(3, admin.getMail());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }
            statement.setInt(4, admin.getId());

            isEdited = statement.executeUpdate() == 1;

            logger.debug(isEdited ? "Admin " + admin.getId() + " was updated"
                    : "Admin " + admin.getId() + " was not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean deleteUser(int userId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
            statement.setInt(1, userId);
            isDeleted = statement.executeUpdate() == 1;
            logger.debug(isDeleted ? "User with id {} has been deleted"
                    : "Can't delete user with id {}", userId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    /**
     * Restore user.
     *
     * @param userId the user id
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    @Override
    public boolean restoreUser(int userId) throws DaoException {
        boolean isRestored;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_RESTORE_USER)) {
            statement.setInt(1, userId);
            isRestored = statement.executeUpdate() == 1;
            logger.debug(isRestored ? "User with id {} has been restored"
                    : "Can't restore user with id {}", userId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRestored;
    }

    /**
     * Gets the admin from result set.
     *
     * @param resultSet the result set
     * @return the admin from result set
     * @throws SQLException the SQL exception
     */
    private Admin getAdminFromResultSet(ResultSet resultSet) throws SQLException {
        return new Admin.Builder()
                .id(resultSet.getInt(USER_ID))
                .login(resultSet.getString(USER_LOGIN))
                .password(resultSet.getString(USER_PASSWORD))
                .role(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()))
                .mail(resultSet.getString(MAIL))
                .name(resultSet.getString(USER_NAME))
                .surname(resultSet.getString(USER_SURNAME))
                .isActive(resultSet.getBoolean(IS_ACTIVE))
                .photo(resultSet.getBytes(PHOTO))
                .verification(resultSet.getBoolean(VERIFICATION))
                .base64Image(resultSet.getBytes(PHOTO) != null
                        ? IMAGE_SRC_PREFIX + Base64.getEncoder().encodeToString(resultSet.getBytes(PHOTO))
                        : DEFAULT_IMAGE)
                .build();
    }
}
