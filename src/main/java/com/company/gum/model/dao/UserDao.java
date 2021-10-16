package com.company.gum.model.dao;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.User;

import java.util.List;

/**
 * The Interface UserDao.
 *
 * @author Vladislav Kuzmich
 */
public interface UserDao {

	/**
	 * The image src prefix.
	 */
	String IMAGE_SRC_PREFIX = "data:image/jpg;base64,";

	/**
	 * The default image.
	 */
	String DEFAULT_IMAGE = "https://i.ibb.co/mDTmCZn/png-transparent-computer-icons-user-profile-user-avatar-blue-heroes-electric-blue.png";

	/**
	 * Find user by id.
	 *
	 * @param userId the user id
	 * @return the user
	 * @throws DaoException the dao exception
	 */
	User findUserById(int userId) throws DaoException;

	/**
	 * Find user by login.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the user
	 * @throws DaoException the dao exception
	 */
	User findUserByLoginAndPassword(String login, String password) throws DaoException;

	/**
	 * Update user password.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean updateUserPassword(User user) throws DaoException;

	/**
	 * Update user image.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean updateUserImage(User user) throws DaoException;

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean deleteUser(int userId) throws DaoException;

	/**
	 * Restore user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean restoreUser(int userId) throws DaoException;

	/**
	 * Find all user.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<User> findAllUser() throws DaoException;
}
