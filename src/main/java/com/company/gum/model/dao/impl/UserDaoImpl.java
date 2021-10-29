package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.dao.UserDao;
import com.company.gum.model.entity.User;
import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.company.gum.model.dao.TableColumnName.*;

/**
 * The Class UserDaoImpl.
 *
 * @author Vladislav Kuzmich
 */
public class UserDaoImpl implements UserDao {

	/**
	 * The Constant logger.
	 */
	private static final Logger logger = LogManager.getLogger();

	/**
	 * The Constant SQL_FIND_USER_BY_ID.
	 */
	private static final String SQL_FIND_USER_BY_ID = "SELECT user_id,\n"
			+ "       login,\n"
			+ "       password,\n"
			+ "       role,\n"
			+ "       name,\n"
			+ "       surname,\n"
			+ "       is_active,\n"
			+ "       mail,\n"
			+ "       image,\n"
			+ "       is_verified\n"
			+ "FROM users\n"
			+ "WHERE user_id = ?";

	/**
	 * The Constant SQL_FIND_USER_BY_LOGIN_AND_PASSWORD.
	 */
	private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT user_id,\n"
			+ "       login,\n"
			+ "       password,\n"
			+ "       role,\n"
			+ "       name,\n"
			+ "       surname,\n"
			+ "       is_active,\n"
			+ "       mail,\n"
			+ "       image,\n"
			+ "       is_verified\n"
			+ "FROM users\n"
			+ "WHERE login = ? AND password = ?";

	/**
	 * The Constant SQL_UPDATE_USER_PASSWORD.
	 */
	private static final String SQL_UPDATE_USER_PASSWORD = "UPDATE users\n"
			+ "SET password = IFNULL(?, password)\n"
			+ "WHERE user_id = ?";

	/**
	 * The Constant SQL_DELETE_USER.
	 */
	private static final String SQL_DELETE_USER = "UPDATE users\n"
			+ "SET is_active = false\n"
			+ "WHERE user_id = ?";

	/**
	 * The Constant SQL_RESTORE_USER.
	 */
	private static final String SQL_RESTORE_USER = "UPDATE users\n"
			+ "SET is_active = true\n"
			+ "WHERE user_id = ?";

	/**
	 * The Constant SQL_UPDATE_USER_IMAGE.
	 */
	private static final String SQL_UPDATE_USER_IMAGE = "UPDATE users\n"
			+ "SET image = IFNULL(?, image)\n"
			+ "WHERE user_id = ?";

	/**
	 * The Constant SQL_FIND_ALL_USER.
	 */
	private static final String SQL_FIND_ALL_USER = "SELECT user_id,\n"
			+ "       login,\n"
			+ "       password,\n"
			+ "       image,\n"
			+ "       role,\n"
			+ "       name,\n"
			+ "       surname,\n"
			+ "       is_active,\n"
			+ "       mail,\n"
			+ "       is_verified\n"
			+ "FROM users\n"
			+ "WHERE role = 'CLIENT'\n"
			+ "   OR role = 'TRAINER';";

	/**
	 * The instance.
	 */
	private static UserDaoImpl instance;

	/**
	 * Instantiates a new user dao impl.
	 */
	private UserDaoImpl() {
	}

	/**
	 * Gets the single instance of UserDaoImpl.
	 *
	 * @return single instance of UserDaoImpl
	 */
	public static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	/**
	 * Find user by id.
	 *
	 * @param userId the user id
	 * @return the user
	 * @throws DaoException the dao exception
	 */
	@Override
	public User findUserById(int userId) throws DaoException {
		User user = null;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = getUserFromResultSet(resultSet);
				logger.debug("User with id \"{}\" was found", userId);
			} else {
				logger.debug("User with id \"{}\" was not found", userId);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return user;
	}

	/**
	 * Find user by login and password.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the user
	 * @throws DaoException the dao exception
	 */
	@Override
	public User findUserByLoginAndPassword(String login, String password) throws DaoException {
		User user = null;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD)) {
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = getUserFromResultSet(resultSet);
				logger.debug("User was found");
			} else {
				logger.debug("User was not found");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return user;
	}

	/**
	 * Update user password.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean updateUserPassword(User user) throws DaoException {
		boolean isUpdated;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
			statement.setString(1, user.getPassword());
			statement.setInt(2, user.getId());
			isUpdated = statement.executeUpdate() == 1;
			logger.debug(isUpdated ? "Password for user with id " + user.getId() + " has been updated"
					: "Can't update password for user with id " + user.getId());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isUpdated;
	}

	/**
	 * Update user image.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean updateUserImage(User user) throws DaoException {
		boolean isUpdated;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_IMAGE)) {
			statement.setBytes(1, user.getPhoto());
			statement.setInt(2, user.getId());
			isUpdated = statement.executeUpdate() == 1;
			if (isUpdated) {
				logger.debug("Profile image path for user with id \"{}\" has been updated", user.getId());
			} else {
				logger.debug("Can't update image path for user with id \"{}\"", user.getId());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isUpdated;
	}

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean deleteUser(int userId) throws DaoException {
		boolean isDeleted;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
			statement.setInt(1, userId);
			isDeleted = statement.executeUpdate() == 1;
			logger.debug(isDeleted ? "User with id {} has been deleted"
					: "Can't delete user with id {}", userId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isDeleted;
	}

	/**
	 * Restore user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean restoreUser(int userId) throws DaoException {
		boolean isRestored;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_RESTORE_USER)) {
			statement.setInt(1, userId);
			isRestored = statement.executeUpdate() == 1;
			logger.debug(isRestored ? "User with id {} has been restored"
					: "Can't restore user with id {}", userId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isRestored;
	}

	/**
	 * Find all user.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<User> findAllUser() throws DaoException {
		List<User> resultArray = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USER)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User client = getUserFromResultSet(resultSet);
				resultArray.add(client);
			}
			logger.debug(resultArray.isEmpty() ? "No users found"
					: "Found {} users", resultArray.size());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return resultArray;
	}

	/**
	 * Gets the user from result set.
	 *
	 * @param resultSet the result set
	 * @return the user from result set
	 * @throws SQLException the SQL exception
	 */
	private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
		return new User.Builder()
				.id(resultSet.getInt(USER_ID))
				.login(resultSet.getString(USER_LOGIN))
				.password(resultSet.getString(USER_PASSWORD))
				.role(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()))
				.mail(resultSet.getString(MAIL))
				.name(resultSet.getString(USER_NAME))
				.surname(resultSet.getString(USER_SURNAME))
				.isActive(resultSet.getBoolean(IS_ACTIVE))
				.photo(resultSet.getBytes(PHOTO))
				.verification(resultSet.getBoolean(VERIFICATION))
				.photoBase64Image(resultSet.getBytes(PHOTO) != null
						? IMAGE_SRC_PREFIX + Base64.getEncoder().encodeToString(resultSet.getBytes(PHOTO))
						: DEFAULT_IMAGE)
				.build();
	}
}