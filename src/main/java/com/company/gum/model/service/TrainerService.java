package com.company.gum.model.service;

import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Trainer;

import java.util.List;

/**
 * The Interface TrainerService.
 *
 * @author Vladislav Kuzmich
 */
public interface TrainerService {

    /**
     * The mail message part 1.
     */
    String MAIL_MESSAGE_PART_1 = "Hello, we are glad to welcome you.\n To access our site as a trainer Use your login: \"";

    /**
     * The mail message part 2.
     */
    String MAIL_MESSAGE_PART_2 = "\", and password: \"";

    /**
     * The mail message part 3.
     */
    String MAIL_MESSAGE_PART_3 = "\"\nClick on this link to verify your account: <a href='http://localhost:8080/gum/controller?command=verification&userId=";

    /**
     * The mail message part 4.
     */
    String MAIL_MESSAGE_PART_4 = "\"\nClick on this link to verify your account: <a href='http://localhost:8080/gum/controller?command=verification&userId=";

    /**
     * Creates the trainer.
     *
     * @param trainer the trainer
     * @return the trainer
     * @throws ServiceException the service exception
     */
    Trainer createTrainer(Trainer trainer) throws ServiceException;

    /**
     * Edits the description.
     *
     * @param trainerId   the trainer id
     * @param description the description
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean editDescription(int trainerId, String description) throws ServiceException;

    /**
     * Edits the experience.
     *
     * @param trainerId  the trainer id
     * @param experience the experience
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean editExperience(int trainerId, String experience) throws ServiceException;

    /**
     * Edits the trainer.
     *
     * @param trainer the trainer
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean editTrainer(Trainer trainer) throws ServiceException;

    /**
     * Find trainer by id.
     *
     * @param trainerId the trainer id
     * @return the trainer
     * @throws ServiceException the service exception
     */
    Trainer findTrainerById(int trainerId) throws ServiceException;

    /**
     * Find all active trainer.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Trainer> findAllActiveTrainer() throws ServiceException;
}
