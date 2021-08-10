package com.company.gum.dao.impl;

import com.company.gum.dao.TrainerDao;
import com.company.gum.entity.user_impl.Trainer;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class TrainerDaoImpl implements TrainerDao {

    private static final Logger logger = LogManager.getLogger();

    private static TrainerDao trainerDao = new TrainerDaoImpl();

    private TrainerDaoImpl() {
    }

    public static TrainerDao getInstance() {
        return trainerDao;
    }

    @Override
    public Trainer create(Trainer trainer) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement userStatement = connection.prepareStatement("INSERT INTO users (login, password, role, is_active) VALUES (?, ?, ?, true)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement trainerStatement = connection.prepareStatement("INSERT INTO trainers(trainer_id, phone_number) VALUES (?, ?)")) {
            try {
                connection.setAutoCommit(false);
                userStatement.setString(1, trainer.getLogin());
                userStatement.setString(1, trainer.getPassword());
                userStatement.setString(1, trainer.getRole().name());
                userStatement.execute();

                ResultSet resultSet = userStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int trainerId = resultSet.getInt(1);
                    trainer.setId(trainerId);
                }

                trainerStatement.setInt(1, trainer.getId());
                trainerStatement.setInt(2, trainer.getPhone());

                trainerStatement.execute();

                connection.commit();
                logger.debug("Trainer created = {}", trainer);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return trainer;
    }

    @Override
    public boolean update(Trainer trainer) {
        return false;
    }

    @Override
    public Trainer findTrainerById(int trainerId) {
        return null;
    }

    @Override
    public List<Trainer> findAllTrainer() {
        return null;
    }

    @Override
    public List<Trainer> findAllActiveTrainer() {
        return null;
    }
}
