package com.company.gum.command.impl.trainer;

import static com.company.gum.command.Router.RouterType.FORWARD;

import java.util.List;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Trainer;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.TrainerServiceImpl;

public class FindAllTrainerCommand implements Command {

	private TrainerService trainerService = TrainerServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		try {
			List<Trainer> trainers = trainerService.findAllActiveTrainer();
			requestContent.putAttribute(AttributeName.TRAINERS, trainers);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return new Router(PagePath.CLIENT_PROFILE, FORWARD);
	}
}
