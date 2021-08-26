package com.company.gum.service.impl;

import com.company.gum.dao.TrainerDao;
import com.company.gum.dao.impl.TrainerDaoImpl;
import com.company.gum.entity.Trainer;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.util.JBCryptPasswordEncoder;

import java.util.List;

public class TrainerServiceImpl implements TrainerService {

    private static TrainerServiceImpl mInstance;

    private TrainerDao trainerDao = TrainerDaoImpl.getInstance();

    private TrainerServiceImpl() {
    }

    public static TrainerServiceImpl getInstance() {
        if (mInstance == null) {
            mInstance = new TrainerServiceImpl();
        }
        return mInstance;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) throws ServiceException {
        Trainer createdTrainer;
        trainer.setPassword(JBCryptPasswordEncoder.encode(trainer.getPassword()));
        try {
            createdTrainer = trainerDao.createTrainer(trainer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdTrainer;
    }

    @Override
    public boolean updateTrainer(Trainer trainer) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = trainerDao.updateTrainer(trainer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public Trainer findTrainerById(int trainerId) throws ServiceException {
        Trainer trainer;
        try {
            trainer = trainerDao.findTrainerById(trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainer;
    }

    @Override
    public List<Trainer> findAllTrainer() throws ServiceException {
        List<Trainer> trainers;
        try {
            trainers = trainerDao.findAllTrainer();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainers;
    }

    @Override
    public List<Trainer> findAllActiveTrainer() throws ServiceException {
        List<Trainer> trainers;
        try {
            trainers = trainerDao.findAllActiveTrainer();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return trainers;
    }
}
