package com.company.gum.service;

import com.company.gum.entity.Trainer;
import com.company.gum.exception.ServiceException;

import java.util.List;

public interface TrainerService {
    String MAIL_MESSAGE_PART_1 = "Hello, we are glad to welcome you.\n To access our site as a trainer Use your login: \"";

    String MAIL_MESSAGE_PART_2 = "\", and password: \"";

    String MAIL_MESSAGE_PART_3 = "\"\nClick on this link to verify your account: <a href='http://localhost:8080/gum/controller?command=verification&userId=";

    String MAIL_MESSAGE_PART_4 = "\"\nClick on this link to verify your account: <a href='http://localhost:8080/gum/controller?command=verification&userId=";

    Trainer createTrainer(Trainer trainer) throws ServiceException;

    boolean editDescription(int trainerId, String description) throws ServiceException;

    boolean editExperience(int trainerId, String experience) throws ServiceException;

    boolean editTrainer(Trainer trainer) throws ServiceException;

    Trainer findTrainerById(int trainerId) throws ServiceException;

    List<Trainer> findAllTrainer() throws ServiceException;

    List<Trainer> findAllActiveTrainer() throws ServiceException;
}
