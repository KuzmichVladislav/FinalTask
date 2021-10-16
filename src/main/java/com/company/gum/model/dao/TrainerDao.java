package com.company.gum.model.dao;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Trainer;

import java.util.List;

/**
 * The Interface TrainerDao.
 *
 * @author Vladislav Kuzmich
 */
public interface TrainerDao {

	/**
	 * The image src prefix.
	 */
	String IMAGE_SRC_PREFIX = "data:image/jpg;base64,";

	/**
	 * The default image.
	 */
	String DEFAULT_IMAGE = "https://i.ibb.co/mDTmCZn/png-transparent-computer-icons-user-profile-user-avatar-blue-heroes-electric-blue.png";

	/**
	 * Creates the trainer.
	 *
	 * @param trainer the trainer
	 * @return the trainer
	 * @throws DaoException the dao exception
	 */
	Trainer createTrainer(Trainer trainer) throws DaoException;

	/**
	 * Edits the description.
	 *
	 * @param trainerId   the trainer id
	 * @param description the description
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean editDescription(int trainerId, String description) throws DaoException;

	/**
	 * Edits the experience.
	 *
	 * @param trainerId  the trainer id
	 * @param experience the experience
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean editExperience(int trainerId, String experience) throws DaoException;

	/**
	 * Edits the trainer.
	 *
	 * @param trainer the trainer
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean editTrainer(Trainer trainer) throws DaoException;

	/**
	 * Find trainer by id.
	 *
	 * @param trainerId the trainer id
	 * @return the trainer
	 * @throws DaoException the dao exception
	 */
	Trainer findTrainerById(int trainerId) throws DaoException;

	/**
	 * Find all trainer.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Trainer> findAllTrainer() throws DaoException;

	/**
	 * Find all active trainer.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Trainer> findAllActiveTrainer() throws DaoException;
}