package com.company.gum.dao;

import com.company.gum.entity.User;
import com.company.gum.exception.DaoException;

public interface UserDao {

    User findUserById(int userId) throws DaoException;

    User findUserByLogin(String login) throws DaoException;

    User findUserByLoginAndPassword(String login, String password) throws DaoException;

    boolean updateUserPassword(User user) throws DaoException;

    boolean updateUserImage(User user) throws DaoException;

    boolean deleteUser(int userId) throws DaoException;

    boolean restoreUser(int userId) throws DaoException;
}
