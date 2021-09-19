package com.company.gum.dao.impl;

import com.company.gum.dao.TrainerDao;
import com.company.gum.entity.Trainer;
import com.company.gum.entity.User;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.gum.dao.TableColumnName.*;

public class TrainerDaoImpl implements TrainerDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_CREATE_USER = "INSERT INTO users(login, password, name, surname, mail, role)\n"
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_CREATE_TRAINER = "INSERT INTO trainers(trainer_id, phone_number)\n"
            + "VALUES (?, ?, ?, ?)";

    private static final String SQL_EDIT_DESCRIPTION = "UPDATE trainers\n"
            + "SET description  = IFNULL(?, description)\n"
            + "WHERE trainer_id = ?\n";

    private static final String SQL_EDIT_TRAINER = "UPDATE trainers, users\n"
            + "SET name  = IFNULL(?, name),\n"
            + "    surname      = IFNULL(?, surname),\n"
            + "    phone_number      = IFNULL(?, phone_number),\n"
            + "    mail = IFNULL(?, mail)\n"
            + "WHERE user_id = ?\n";
    private static final String SQL_FIND_TRAINER_BY_ID = "SELECT trainer_id,\n"
            + "       register_date,\n"
            + "       phone_number,\n"
            + "       login,\n"
            + "       password,\n"
            + "       role,\n"
            + "       name,\n"
            + "       surname,\n"
            + "       is_active,\n"
            + "       profile_image,\n"
            + "       mail,\n"
            + "       description,\n"
            + "       experience,\n"
            + "       is_verified\n"
            + "FROM trainers\n"
            + "     JOIN users on user_id = trainer_id\n"
            + "WHERE trainer_id = ?\n"
            + "  AND role = 'TRAINER'";
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
            + "       is_verified\n"
            + "FROM trainers\n"
            + "     JOIN users on user_id = trainer_id\n"
            + "WHERE role = 'TRAINER'";
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
            + "       is_verified\n"
            + "FROM trainers\n"
            + "     JOIN users on user_id = trainer_id\n"
            + "WHERE is_active = true\n"
            + "  AND role = 'TRAINER'";

    private static TrainerDaoImpl instance;

    private TrainerDaoImpl() {
    }

    public static TrainerDaoImpl getInstance() {
        if (instance == null) {
            instance = new TrainerDaoImpl();
        }
        return instance;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
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
                    int clientId = resultSet.getInt(1);
                    trainer.setId(clientId);
                }

                trainerStatement.setInt(1, trainer.getId());
                if (trainer.getPhone() != null) {
                    trainerStatement.setString(2, trainer.getPhone());
                } else {
                    trainerStatement.setNull(2, Types.NULL);
                }
                if (trainer.getPhone() != null) {
                    trainerStatement.setString(3, trainer.getDescription());
                } else {
                    trainerStatement.setNull(3, Types.NULL);
                }
                if (trainer.getPhone() != null) {
                    trainerStatement.setString(4, trainer.getExperience());
                } else {
                    trainerStatement.setNull(4, Types.NULL);
                }
                trainerStatement.execute();

                connection.commit();
                logger.debug("Trainer {} was created", trainer);
            } catch (SQLException e) {
                logger.debug("Trainer was not created");
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

            logger.debug(isEdited ? "Trainer " + trainerId + " was updated" : "Trainer " + trainerId + " was not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

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

            logger.debug(isEdited ? "Trainer " + trainer.getId() + " was updated" : "Trainer " + trainer.getId() + " was not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isEdited;
    }

    @Override
    public Trainer findTrainerById(int trainerId) throws DaoException {
        Trainer trainer = new Trainer();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_TRAINER_BY_ID)) {

            statement.setInt(1, trainerId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trainer = getTrainerFromResultSet(resultSet);
                logger.debug("Trainer with id \"{}\" was found:\n{}", trainerId, trainer);
            } else {
                logger.debug("Trainer with id \"{}\" was not found:\n{}", trainerId, trainer);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return trainer;
    }

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

            logger.debug(resultArray.isEmpty() ? "No clients found" : "Found {} clients:\n{}", resultArray.size(), resultArray);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultArray;
    }

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

            logger.debug(resultArray.isEmpty() ? "No active trainer found" : "Found {} active trainers:\n{}", resultArray.size(), resultArray);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultArray;
    }

    private Trainer getTrainerFromResultSet(ResultSet resultSet) throws SQLException {
        Trainer trainer = new Trainer();
        trainer.setId(resultSet.getInt(TRAINER_ID));
        trainer.setLogin(resultSet.getString(USER_LOGIN));
        trainer.setPassword(resultSet.getString(USER_PASSWORD));
        trainer.setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
        trainer.setMail(resultSet.getString(MAIL));
        trainer.setName(resultSet.getString(USER_NAME));
        trainer.setSurname(resultSet.getString(USER_SURNAME));
        trainer.setActive(resultSet.getBoolean(IS_ACTIVE));
        trainer.setRegisterDate(resultSet.getTimestamp(REGISTER_DATE).toLocalDateTime());
        trainer.setPhone(resultSet.getString(PHONE_NUMBER));
        trainer.setDescription(resultSet.getString(DESCRIPTION));
        trainer.setExperience(resultSet.getString(EXPERIENCE));
        return trainer;
    }
}
