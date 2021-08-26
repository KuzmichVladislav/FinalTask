package com.company.gum.dao.impl;

import com.company.gum.dao.AdminDao;
import com.company.gum.entity.Admin;
import com.company.gum.entity.User;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.gum.dao.TableColumnName.*;

public class AdminDaoImpl implements AdminDao {

    private static final String SQL_FIND_ADMIN_BY_ID = "SELECT user_id,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
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
            + "       profile_image,\n"
            + "       mail\n"
            + "FROM users\n"
            + "WHERE role = 'ADMIN'";

    private static final Logger logger = LogManager.getLogger();
    private static AdminDaoImpl mInstance;

    private AdminDaoImpl() {
    }

    public static AdminDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new AdminDaoImpl();
        }
        return mInstance;
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
                logger.info("Admin with id \"{}\" was found", admin.getId());
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
            if (resultArray.isEmpty()) {
                logger.debug("No admin found");
            } else {
                logger.debug("Found {} admin:\n{}", resultArray.size(), resultArray);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultArray;
    }

    private Admin getAdminFromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getInt(USER_ID));
        admin.setLogin(resultSet.getString(USER_LOGIN));
        admin.setPassword(resultSet.getString(USER_PASSWORD));
        admin.setProfileImage(resultSet.getString(PROFILE_IMAGE));
        admin.setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
        admin.setMail(resultSet.getString(MAIL));
        admin.setName(resultSet.getString(USER_NAME));
        admin.setSurname(resultSet.getString(USER_SURNAME));
        admin.setActive(resultSet.getBoolean(IS_ACTIVE));
        return admin;
    }
}
