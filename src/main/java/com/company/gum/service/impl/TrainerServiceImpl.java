package com.company.gum.service.impl;

import com.company.gum.dao.TrainerDao;
import com.company.gum.dao.impl.TrainerDaoImpl;
import com.company.gum.entity.Trainer;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.util.JBCryptPasswordEncoder;
import com.company.gum.util.MailSender;

import java.util.List;

public class TrainerServiceImpl implements TrainerService {

    private static TrainerServiceImpl instance;

    private TrainerDao trainerDao = TrainerDaoImpl.getInstance();

    private TrainerServiceImpl() {
    }

    public static TrainerServiceImpl getInstance() {
        if (instance == null) {
            instance = new TrainerServiceImpl();
        }
        return instance;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) throws ServiceException {
        Trainer createdTrainer;
        String password = trainer.getPassword();
        try {
            trainer.setPassword(JBCryptPasswordEncoder.encode(password));
            createdTrainer = trainerDao.createTrainer(trainer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        MailSender sender = new MailSender();
        sender.send(trainer.getId(), trainer.getMail(),
                MAIL_MESSAGE_PART_1 + trainer.getLogin()
                        + MAIL_MESSAGE_PART_2 + password
                        + MAIL_MESSAGE_PART_3 + trainer.getId()
                        + MAIL_MESSAGE_PART_4);
        return createdTrainer;
    }

    @Override
    public boolean editDescription(int trainerId, String description) throws ServiceException {
        boolean isEdited;
        try {
            isEdited = trainerDao.editDescription(trainerId, description);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isEdited;
    }

    @Override
    public boolean editExperience(int trainerId, String experience) throws ServiceException {
        boolean isEdited;
        try {
            isEdited = trainerDao.editExperience(trainerId, experience);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isEdited;
    }

    @Override
    public boolean editTrainer(Trainer trainer) throws ServiceException {
        boolean isEdited;
        try {
            isEdited = trainerDao.editTrainer(trainer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isEdited;
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
