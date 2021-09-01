package com.company.gum.service.impl;

import com.company.gum.dao.UserDao;
import com.company.gum.dao.impl.UserDaoImpl;
import com.company.gum.entity.User;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.UserService;
import com.company.gum.util.JBCryptPasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private static UserServiceImpl mInstance;

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (mInstance == null) {
            mInstance = new UserServiceImpl();
        }
        return mInstance;
    }

    @Override
    public User findUserById(int userId) throws ServiceException {
        User user;
        try {
            user = userDao.findUserById(userId);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        User user;
        try {
            user = userDao.findUserByLogin(login);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean updateUserPassword(User user) throws ServiceException {
        boolean isUpdated;
        try {
            user.setPassword(JBCryptPasswordEncoder.encode(user.getPassword()));
            isUpdated = userDao.updateUserPassword(user);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserImage(User user) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.updateUserImage(user);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteUser(int userId) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = userDao.deleteUser(userId);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean restoreUser(int userId) throws ServiceException {
        boolean isRestored;
        try {
            isRestored = userDao.restoreUser(userId);
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return isRestored;
    }
}
