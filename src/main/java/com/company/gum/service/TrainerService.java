package com.company.gum.service;

import com.company.gum.entity.Trainer;
import com.company.gum.exception.ServiceException;

import java.util.List;

public interface TrainerService {

	Trainer createTrainer(Trainer trainer) throws ServiceException;

	boolean updateTrainer(Trainer trainer) throws ServiceException;

	Trainer findTrainerById(int trainerId) throws ServiceException;

	List<Trainer> findAllTrainer() throws ServiceException;

	List<Trainer> findAllActiveTrainer() throws ServiceException;
}
