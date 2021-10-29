package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.dao.TrainerDao;
import com.company.gum.model.entity.Trainer;
import com.company.gum.model.entity.User;
import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.company.gum.model.dao.TableColumnName.*;

/**
 * The Class TrainerDaoImpl.
 *
 * @author Vladislav Kuzmich
 */
public class TrainerDaoImpl implements TrainerDao {

	/**
	 * The Constant logger.
	 */
	private static final Logger logger = LogManager.getLogger();

	/**
	 * The Constant SQL_CREATE_USER.
	 */
	private static final String SQL_CREATE_USER = "INSERT INTO users(login, password, name, surname, mail, role)\n"
			+ "VALUES (?, ?, ?, ?, ?, ?)";

	/**
	 * The Constant SQL_CREATE_TRAINER.
	 */
	private static final String SQL_CREATE_TRAINER = "INSERT INTO trainers(trainer_id, phone_number)\n"
			+ "VALUES (?, ?)";

	/**
	 * The Constant SQL_EDIT_DESCRIPTION.
	 */
	private static final String SQL_EDIT_DESCRIPTION = "UPDATE trainers\n"
			+ "SET description  = IFNULL(?, description)\n"
			+ "WHERE trainer_id = ?\n";

	/**
	 * The Constant SQL_EDIT_EXPERIENCE.
	 */
	private static final String SQL_EDIT_EXPERIENCE = "UPDATE trainers\n"
			+ "SET experience  = IFNULL(?, experience)\n"
			+ "WHERE trainer_id = ?\n";

	/**
	 * The Constant SQL_EDIT_TRAINER.
	 */
	private static final String SQL_EDIT_TRAINER = "UPDATE trainers, users\n"
			+ "SET name  = IFNULL(?, name),\n"
			+ "    surname      = IFNULL(?, surname),\n"
			+ "    phone_number      = IFNULL(?, phone_number),\n"
			+ "    mail = IFNULL(?, mail)\n"
			+ "WHERE user_id = ?\n";

	/**
	 * The Constant SQL_FIND_TRAINER_BY_ID.
	 */
	private static final String SQL_FIND_TRAINER_BY_ID = "SELECT trainer_id,\n"
			+ "       register_date,\n"
			+ "       phone_number,\n"
			+ "       login,\n"
			+ "       password,\n"
			+ "       role,\n"
			+ "       name,\n"
			+ "       surname,\n"
			+ "       is_active,\n"
			+ "       image,\n"
			+ "       mail,\n"
			+ "       description,\n"
			+ "       experience,\n"
			+ "       is_verified\n"
			+ "FROM trainers\n"
			+ "     JOIN users on user_id = trainer_id\n"
			+ "WHERE trainer_id = ?\n"
			+ "  AND role = 'TRAINER'";

	/**
	 * The Constant SQL_FIND_ALL_TRAINER.
	 */
	private static final String SQL_FIND_ALL_TRAINER = "SELECT trainer_id,\n"
			+ "       register_date,\n"
			+ "       phone_number,\n"
			+ "       login,\n"
			+ "       password,\n"
			+ "       role,\n"
			+ "       name,\n"
			+ "       surname,\n"
			+ "       is_active,\n"
			+ "       mail,\n"
			+ "       description,\n"
			+ "       experience,\n"
			+ "       image,\n"
			+ "       is_verified\n"
			+ "FROM trainers\n"
			+ "       JOIN users on user_id = trainer_id\n"
			+ "WHERE role = 'TRAINER'";

	/**
	 * The Constant SQL_FIND_ALL_ACTIVE_TRAINER.
	 */
	private static final String SQL_FIND_ALL_ACTIVE_TRAINER = "SELECT trainer_id,\n"
			+ "       register_date,\n"
			+ "       phone_number,\n"
			+ "       login,\n"
			+ "       password,\n"
			+ "       role,\n"
			+ "       name,\n"
			+ "       surname,\n"
			+ "       is_active,\n"
			+ "       mail,\n"
			+ "       description,\n"
			+ "       experience,\n"
			+ "       image,\n"
			+ "       is_verified\n"
			+ "FROM trainers\n"
			+ "       JOIN users on user_id = trainer_id\n"
			+ "WHERE is_active = true\n"
			+ "  AND role = 'TRAINER'";

	/**
	 * The instance.
	 */
	private static TrainerDaoImpl instance;

	/**
	 * Instantiates a new trainer dao impl.
	 */
	private TrainerDaoImpl() {
	}

	/**
	 * Gets the single instance of TrainerDaoImpl.
	 *
	 * @return single instance of TrainerDaoImpl
	 */
	public static TrainerDaoImpl getInstance() {
		if (instance == null) {
			instance = new TrainerDaoImpl();
		}
		return instance;
	}

	/**
	 * Creates the trainer.
	 *
	 * @param trainer the trainer
	 * @return the trainer
	 * @throws DaoException the dao exception
	 */
	@Override
	public Trainer createTrainer(Trainer trainer) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement userStatement = connection.prepareStatement(SQL_CREATE_USER,
				     Statement.RETURN_GENERATED_KEYS);
		     PreparedStatement trainerStatement = connection.prepareStatement(SQL_CREATE_TRAINER)) {
			try {
				connection.setAutoCommit(false);
				userStatement.setString(1, trainer.getLogin());
				userStatement.setString(2, trainer.getPassword());
				userStatement.setString(3, trainer.getName());
				userStatement.setString(4, trainer.getSurname());
				userStatement.setString(5, trainer.getMail());
				userStatement.setString(6, trainer.getRole().name());
				userStatement.execute();
				ResultSet resultSet = userStatement.getGeneratedKeys();
				if (resultSet.next()) {
					int trainerId = resultSet.getInt(1);
					trainer.setId(trainerId);
				}
				trainerStatement.setInt(1, trainer.getId());
				if (trainer.getPhone() != null) {
					trainerStatement.setString(2, trainer.getPhone());
				} else {
					trainerStatement.setNull(2, Types.VARCHAR);
				}
				trainerStatement.execute();
				connection.commit();
				logger.debug("Trainer with id {} was created", trainer.getId());
			} catch (SQLException e) {
				logger.debug("Trainer was not created", e);
				connection.rollback();
				throw new DaoException(e);
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return trainer;
	}

	/**
	 * Edits the description.
	 *
	 * @param trainerId   the trainer id
	 * @param description the description
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean editDescription(int trainerId, String description) throws DaoException {
		boolean isEdited;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_EDIT_DESCRIPTION)) {
			if (description != null) {
				statement.setString(1, description);
			} else {
				statement.setNull(1, Types.VARCHAR);
			}
			statement.setInt(2, trainerId);
			isEdited = statement.executeUpdate() == 1;
			logger.debug(isEdited ? "Trainer description {} was updated"
					: "Trainer description {} was not updated", trainerId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isEdited;
	}

	/**
	 * Edits the experience.
	 *
	 * @param trainerId  the trainer id
	 * @param experience the experience
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean editExperience(int trainerId, String experience) throws DaoException {
		boolean isEdited;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_EDIT_EXPERIENCE)) {
			if (experience != null) {
				statement.setString(1, experience);
			} else {
				statement.setNull(1, Types.VARCHAR);
			}
			statement.setInt(2, trainerId);
			isEdited = statement.executeUpdate() == 1;
			logger.debug(isEdited ? "Trainer experience {} was updated"
					: "Trainer experience {} was not updated", trainerId);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isEdited;
	}

	/**
	 * Edits the trainer.
	 *
	 * @param trainer the trainer
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean editTrainer(Trainer trainer) throws DaoException {
		boolean isEdited;
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_EDIT_TRAINER)) {
			if (trainer.getName() != null) {
				statement.setString(1, trainer.getName());
			} else {
				statement.setNull(1, Types.VARCHAR);
			}
			if (trainer.getSurname() != null) {
				statement.setString(2, trainer.getSurname());
			} else {
				statement.setNull(2, Types.VARCHAR);
			}
			if (trainer.getPhone() != null) {
				statement.setString(3, trainer.getPhone());
			} else {
				statement.setNull(3, Types.VARCHAR);
			}
			if (trainer.getMail() != null) {
				statement.setString(4, trainer.getMail());
			} else {
				statement.setNull(4, Types.VARCHAR);
			}
			statement.setInt(5, trainer.getId());
			isEdited = statement.executeUpdate() == 1;
			logger.debug(isEdited ? "Trainer " + trainer.getId() + " was updated"
					: "Trainer " + trainer.getId() + " was not updated");
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isEdited;
	}

	/**
	 * Find trainer by id.
	 *
	 * @param trainerId the trainer id
	 * @return the trainer
	 * @throws DaoException the dao exception
	 */
	@Override
	public Trainer findTrainerById(int trainerId) throws DaoException {
		Trainer trainer = new Trainer();
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_TRAINER_BY_ID)) {
			statement.setInt(1, trainerId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				trainer = getTrainerFromResultSet(resultSet);
				logger.debug("Trainer with id \"{}\" was found", trainerId);
			} else {
				logger.debug("Trainer with id \"{}\" was not found", trainerId);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return trainer;
	}

	/**
	 * Find all trainer.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Trainer> findAllTrainer() throws DaoException {
		List<Trainer> resultArray = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_TRAINER)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Trainer trainer = getTrainerFromResultSet(resultSet);
				resultArray.add(trainer);
			}
			logger.debug(resultArray.isEmpty() ? "No clients found"
					: "Found {} clients", resultArray.size());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return resultArray;
	}

	/**
	 * Find all active trainer.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Trainer> findAllActiveTrainer() throws DaoException {
		List<Trainer> resultArray = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_TRAINER)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Trainer trainer = getTrainerFromResultSet(resultSet);
				resultArray.add(trainer);
			}
			logger.debug(resultArray.isEmpty() ? "No active trainer found" : "Found {} active trainers", resultArray.size());
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return resultArray;
	}

	/**
	 * Gets the trainer from result set.
	 *
	 * @param resultSet the result set
	 * @return the trainer from result set
	 * @throws SQLException the SQL exception
	 */
	private Trainer getTrainerFromResultSet(ResultSet resultSet) throws SQLException {
		return new Trainer.Builder()
				.id(resultSet.getInt(TRAINER_ID))
				.login(resultSet.getString(USER_LOGIN))
				.password(resultSet.getString(USER_PASSWORD))
				.role(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()))
				.mail(resultSet.getString(MAIL))
				.name(resultSet.getString(USER_NAME))
				.surname(resultSet.getString(USER_SURNAME))
				.isActive(resultSet.getBoolean(IS_ACTIVE))
				.registerDate(resultSet.getTimestamp(REGISTER_DATE).toLocalDateTime())
				.phone(resultSet.getString(PHONE_NUMBER))
				.verification(resultSet.getBoolean(VERIFICATION))
				.experience(resultSet.getString(EXPERIENCE))
				.description(resultSet.getString(DESCRIPTION))
				.photo(resultSet.getBytes(PHOTO))
				.photoBase64Image(resultSet.getBytes(PHOTO) != null
						? IMAGE_SRC_PREFIX + Base64.getEncoder().encodeToString(resultSet.getBytes(PHOTO))
						: DEFAULT_IMAGE)
				.build();
	}
}