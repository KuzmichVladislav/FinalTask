package com.company.gum.model.service;

import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.User;

import java.util.List;

/**
 * The Interface UserService.
 *
 * @author Vladislav Kuzmich
 */
public interface UserService {

	/**
	 * Find user by id.
	 *
	 * @param userId the user id
	 * @return the user
	 * @throws ServiceException the service exception
	 */
	User findUserById(int userId) throws ServiceException;

	/**
	 * Find user by login and password.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the user
	 * @throws ServiceException the service exception
	 */
	User findUserByLoginAndPassword(String login, String password) throws ServiceException;

	/**
	 * Update user password.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean updateUserPassword(User user) throws ServiceException;

	/**
	 * Update user image.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean updateUserImage(User user) throws ServiceException;

	/**
	 * Find all user.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<User> findAllUser() throws ServiceException;
}