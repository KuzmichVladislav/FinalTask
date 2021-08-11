package com.company.gum.dao.impl;

import com.company.gum.dao.AdminDao;
import com.company.gum.entity.user_impl.Admin;
import com.company.gum.entity.user_impl.UserRole;
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

import static com.company.gum.dao.TableColumnNames.*;

public class AdminDaoImpl implements AdminDao {
    private static final String FIND_ADMIN_BY_ID = "SELECT user_id, login, password, role, name, surname, is_active, profile_image, mail FROM users WHERE user_id = ? AND role = 'ADMIN'";
    private static final String FIND_ALL_ADMIN = "SELECT user_id, login, password, role, name, surname, is_active, profile_image, mail FROM users WHERE role = 'ADMIN'";


    private static final Logger logger = LogManager.getLogger();

    private static AdminDao adminDao = new AdminDaoImpl();

    private AdminDaoImpl() {
    }

    public static AdminDao getInstance() {
        return adminDao;
    }

    @Override
    public Admin findAdminById(int adminId) throws DaoException {
        Admin admin = new Admin();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_ID)) {
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
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN)) {
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
        admin.setId(resultSet.getInt(USERS_ID));
        admin.setLogin(resultSet.getString(USERS_LOGIN));
        admin.setPassword(resultSet.getString(USERS_PASSWORD));
        admin.setProfileImage(resultSet.getString(PROFILE_IMAGE));
        admin.setRole(UserRole.valueOf(resultSet.getString(USERS_ROLE).toUpperCase()));
        admin.setMail(resultSet.getString(MAIL));
        admin.setName(resultSet.getString(USERS_NAME));
        admin.setSurname(resultSet.getString(USERS_SURNAME));
        admin.setActive(resultSet.getBoolean(IS_ACTIVE));
        return admin;
    }
}
