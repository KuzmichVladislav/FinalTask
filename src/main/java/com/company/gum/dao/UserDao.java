package com.company.gum.dao;

import com.company.gum.entity.userImpl.User;
import com.company.gum.exception.DaoException;

public interface UserDao {
    User findById(int userId) throws DaoException;
    User findByLogin(String login) throws DaoException;
    boolean updateUserPassword(User user) throws DaoException;
    boolean updateUserImage(User user);
    boolean delete(int userId) throws DaoException;
    boolean restore(int userId) throws DaoException;

}
