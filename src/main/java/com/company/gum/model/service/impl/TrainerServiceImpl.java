package com.company.gum.model.service.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.dao.TrainerDao;
import com.company.gum.model.dao.impl.TrainerDaoImpl;
import com.company.gum.model.entity.Trainer;
import com.company.gum.model.service.TrainerService;
import com.company.gum.model.util.JBCryptPasswordEncoder;
import com.company.gum.model.util.MailSender;

import java.util.List;

/**
 * The Class TrainerServiceImpl.
 *
 * @author Vladislav Kuzmich
 */
public class TrainerServiceImpl implements TrainerService {

	/**
	 * The instance.
	 */
	private static TrainerServiceImpl instance;

	/**
	 * The trainer dao.
	 */
	private final TrainerDao trainerDao = TrainerDaoImpl.getInstance();

	/**
	 * Instantiates a new trainer service impl.
	 */
	private TrainerServiceImpl() {
	}

	/**
	 * Gets the single instance of TrainerServiceImpl.
	 *
	 * @return single instance of TrainerServiceImpl
	 */
	public static TrainerServiceImpl getInstance() {
		if (instance == null) {
			instance = new TrainerServiceImpl();
		}
		return instance;
	}

	/**
	 * Creates the trainer.
	 *
	 * @param trainer the trainer
	 * @return the trainer
	 * @throws ServiceException the service exception
	 */
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
		sender.send(trainer.getMail(),
				MAIL_MESSAGE_PART_1 + trainer.getLogin()
						+ MAIL_MESSAGE_PART_2 + password
						+ MAIL_MESSAGE_PART_3 + trainer.getId()
						+ MAIL_MESSAGE_PART_4);
		return createdTrainer;
	}

	/**
	 * Edits the description.
	 *
	 * @param trainerId   the trainer id
	 * @param description the description
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Edits the experience.
	 *
	 * @param trainerId  the trainer id
	 * @param experience the experience
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Edits the trainer.
	 *
	 * @param trainer the trainer
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Find trainer by id.
	 *
	 * @param trainerId the trainer id
	 * @return the trainer
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Find all active trainer.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
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
