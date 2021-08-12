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

import static com.company.gum.dao.TableColumnNames.*;

public class TrainerDaoImpl implements TrainerDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String CREATE_USER = "INSERT INTO users(login, password, name, surname, mail, role)\n" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CREATE_TRAINER = "INSERT INTO trainers(trainer_id, phone_number)\n" +
            "VALUES (?, ?)";
    private static final String UPDATE_TRAINER = "UPDATE trainers\n" +
            "SET phone_number = IFNULL(?, phone_number)\n" +
            "WHERE trainer_id = ?";
    private static final String FIND_TRAINER_BY_ID = "SELECT trainer_id,\n" +
            "       register_date,\n" +
            "       phone_number,\n" +
            "       login,\n" +
            "       password,\n" +
            "       role,\n" +
            "       name,\n" +
            "       surname,\n" +
            "       is_active,\n" +
            "       profile_image,\n" +
            "       mail,\n" +
            "       is_verified\n" +
            "FROM trainers,\n" +
            "     users\n" +
            "WHERE trainer_id = ?\n" +
            "  AND role = 'TRAINER'";
    private static final String FIND_ALL_TRAINER = "SELECT trainer_id,\n" +
            "       register_date,\n" +
            "       phone_number,\n" +
            "       login,\n" +
            "       password,\n" +
            "       role,\n" +
            "       name,\n" +
            "       surname,\n" +
            "       is_active,\n" +
            "       profile_image,\n" +
            "       mail,\n" +
            "       is_verified\n" +
            "FROM trainers,\n" +
            "     users\n" +
            "WHERE role = 'TRAINER'";
    private static final String FIND_ALL_ACTIVE_TRAINER = "SELECT trainer_id,\n" +
            "       register_date,\n" +
            "       phone_number,\n" +
            "       login,\n" +
            "       password,\n" +
            "       role,\n" +
            "       name,\n" +
            "       surname,\n" +
            "       is_active,\n" +
            "       profile_image,\n" +
            "       mail,\n" +
            "       is_verified\n" +
            "FROM trainers,\n" +
            "     users\n" +
            "WHERE is_active = true\n" +
            "  AND role = 'TRAINER'";


    private static TrainerDao trainerDao = new TrainerDaoImpl();

    private TrainerDaoImpl() {
    }

    public static TrainerDao getInstance() {
        return trainerDao;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement trainerStatement = connection.prepareStatement(CREATE_TRAINER)) {
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
                trainerStatement.execute();

                connection.commit();
                logger.debug("Trainer {} was created", trainer);
            } catch (SQLException e) {
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
    public boolean updateTrainer(Trainer trainer) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TRAINER)) {
            if (trainer.getName() != null) {
                statement.setString(1, trainer.getPhone());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            statement.setInt(2, trainer.getId());

            isUpdated = statement.execute();

            logger.debug("Trainer {} was updated", trainer);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public Trainer findTrainerById(int trainerId) throws DaoException {
        Trainer trainer = new Trainer();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TRAINER_BY_ID)) {

            statement.setInt(1, trainerId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                trainer = getTrainerFromResultSet(resultSet);
                logger.debug("Сlient with id \"{}\" was found:\n{}", trainerId, trainer);
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
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TRAINER)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Trainer trainer = getTrainerFromResultSet(resultSet);
                resultArray.add(trainer);
            }
            if (resultArray.isEmpty()) {
                logger.debug("No clients found");
            } else {
                logger.debug("Found {} clients:\n{}", resultArray.size(), resultArray);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultArray;
    }

    @Override
    public List<Trainer> findAllActiveTrainer() throws DaoException {
        List<Trainer> resultArray = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_TRAINER)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Trainer trainer = getTrainerFromResultSet(resultSet);
                resultArray.add(trainer);
            }
            if (resultArray.isEmpty()) {
                logger.debug("No active clients found");
            } else {
                logger.debug("Found {} active clients:\n{}", resultArray.size(), resultArray);
            }
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
        trainer.setProfileImage(resultSet.getString(PROFILE_IMAGE));
        trainer.setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
        trainer.setMail(resultSet.getString(MAIL));
        trainer.setName(resultSet.getString(USER_NAME));
        trainer.setSurname(resultSet.getString(USER_SURNAME));
        trainer.setActive(resultSet.getBoolean(IS_ACTIVE));
        trainer.setRegisterDate(resultSet.getTimestamp(REGISTER_DATE).toLocalDateTime());
        trainer.setPhone(resultSet.getString(PHONE_NUMBER));
        return trainer;
    }
}
