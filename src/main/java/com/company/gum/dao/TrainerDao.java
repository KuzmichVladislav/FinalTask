package com.company.gum.dao;

import com.company.gum.entity.Trainer;
import com.company.gum.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface TrainerDao {

    Trainer createTrainer(Trainer trainer) throws DaoException;

    boolean updateTrainer(Trainer trainer) throws DaoException;

    Trainer findTrainerById(int trainerId) throws DaoException;

    List<Trainer> findAllTrainer() throws DaoException;

    List<Trainer> findAllActiveTrainer() throws DaoException;
}
