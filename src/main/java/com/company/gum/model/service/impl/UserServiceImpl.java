package com.company.gum.model.service.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.dao.UserDao;
import com.company.gum.model.dao.impl.UserDaoImpl;
import com.company.gum.model.entity.User;
import com.company.gum.model.service.UserService;
import com.company.gum.model.util.JBCryptPasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The Class UserServiceImpl.
 *
 * @author Vladislav Kuzmich
 */
public class UserServiceImpl implements UserService {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The instance.
     */
    private static UserServiceImpl instance;

    /**
     * The user dao.
     */
    private final UserDao userDao = UserDaoImpl.getInstance();

    /**
     * Instantiates a new user service impl.
     */
    private UserServiceImpl() {
    }

    /**
     * Gets the single instance of UserServiceImpl.
     *
     * @return single instance of UserServiceImpl
     */
    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    /**
     * Find user by id.
     *
     * @param userId the user id
     * @return the user
     * @throws ServiceException the service exception
     */
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

    /**
     * Find user by login and password.
     *
     * @param login the login
     * @param password the password
     * @return the user
     * @throws ServiceException the service exception
     */
    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDao.findUserByLoginAndPassword(login, JBCryptPasswordEncoder.encode(password));
        } catch (DaoException e) {
            logger.warn(e);
            throw new ServiceException(e);
        }
        return user;
    }

    /**
     * Update user password.
     *
     * @param user the user
     * @return true, if successful
     * @throws ServiceException the service exception
     */
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

    /**
     * Update user image.
     *
     * @param user the user
     * @return true, if successful
     * @throws ServiceException the service exception
     */
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

    /**
     * Find all user.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    @Override
    public List<User> findAllUser() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllUser();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }
}
