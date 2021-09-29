package com.company.gum.command.impl.client;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Trainer;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.TrainerServiceImpl;

import java.util.List;

import static com.company.gum.command.AttributeName.TRAINERS;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class NewOrderCommand implements Command {

	private TrainerService trainerService = TrainerServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		Router router;
		try {
			List<Trainer> trainers = trainerService.findAllActiveTrainer();
			requestContent.putAttribute(TRAINERS, trainers);
			router = new Router(PagePath.CREATE_ORDER, FORWARD);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
