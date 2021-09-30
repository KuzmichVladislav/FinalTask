package com.company.gum.dao;

import com.company.gum.entity.Trainer;
import com.company.gum.exception.DaoException;

import java.util.List;

public interface TrainerDao {

    Trainer createTrainer(Trainer trainer) throws DaoException;

    boolean editDescription(int trainerId, String description) throws DaoException;

    boolean editExperience(int trainerId, String experience) throws DaoException;

    boolean editTrainer(Trainer trainer) throws DaoException;

    Trainer findTrainerById(int trainerId) throws DaoException;

    List<Trainer> findAllTrainer() throws DaoException;

    List<Trainer> findAllActiveTrainer() throws DaoException;
}
