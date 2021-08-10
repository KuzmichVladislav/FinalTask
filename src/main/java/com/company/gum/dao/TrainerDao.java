package com.company.gum.dao;

import com.company.gum.entity.user_impl.Trainer;
import com.company.gum.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface TrainerDao {

    Trainer create(Trainer trainer) throws SQLException, DaoException;

    boolean update(Trainer trainer);

    Trainer findTrainerById(int trainerId);

    List<Trainer> findAllTrainer();

    List<Trainer> findAllActiveTrainer();
}
