package com.company.gum.dao.impl;

import com.company.gum.dao.AdminDao;
import com.company.gum.entity.Admin;
import com.company.gum.entity.User;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.gum.dao.TableColumnName.*;

public class AdminDaoImpl implements AdminDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_ADMIN_BY_ID = "SELECT user_id,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       mail\n"
            + "FROM users\n"
            + "WHERE user_id = ?\n"
            + "  AND role = 'ADMIN'";
    private static final String SQL_FIND_ALL_ADMIN = "SELECT user_id,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       mail\n"
            + "FROM users\n"
            + "WHERE role = 'ADMIN'";
    private static final String SQL_EDIT_ADMIN = "UPDATE users\n"
            + "SET name  = IFNULL(?, name),\n"
            + "    surname      = IFNULL(?, surname),\n"
            + "    mail = IFNULL(?, mail)\n"
            + "WHERE user_id = ?\n";

    private static AdminDaoImpl instance;

    private AdminDaoImpl() {
    }

    public static AdminDaoImpl getInstance() {
        if (instance == null) {
            instance = new AdminDaoImpl();
        }
        return instance;
    }

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

    @Override
    public List<Admin> findAllAdmin() throws DaoException {
        List<Admin> resultArray = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ADMIN)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Admin admin = getAdminFromResultSet(resultSet);
                resultArray.add(admin);
            }

            logger.debug(resultArray.isEmpty() ? "No admin found" : "Found {} admin:\n{}", resultArray.size(), resultArray);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultArray;
    }

    @Override
    public boolean editAdmin(Admin admin) throws DaoException {
        boolean isEdited;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_EDIT_ADMIN)) {
            if (admin.getName() != null) {
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

            logger.debug(isEdited ? "Admin " + admin.getId() + " was updated" : "Admin " + admin.getId() + " was not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

    private Admin getAdminFromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt(USER_ID));
        admin.setLogin(resultSet.getString(USER_LOGIN));
        admin.setPassword(resultSet.getString(USER_PASSWORD));
        admin.setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
        admin.setMail(resultSet.getString(MAIL));
        admin.setName(resultSet.getString(USER_NAME));
        admin.setSurname(resultSet.getString(USER_SURNAME));
        admin.setActive(resultSet.getBoolean(IS_ACTIVE));
        return admin;
    }
}
