package com.company.gum.service;

import com.company.gum.entity.User;
import com.company.gum.exception.ServiceException;

public interface UserService {
    User findUserById(int userId) throws ServiceException;

    User findUserByLogin(String login) throws ServiceException;

    boolean updateUserPassword(User user) throws ServiceException;

    boolean updateUserImage(User user) throws ServiceException;

    boolean deleteUser(int userId) throws ServiceException;

    boolean restoreUser(int userId) throws ServiceException;
}