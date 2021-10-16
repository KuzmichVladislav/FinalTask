package com.company.gum.controller.command.impl.admin;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.User;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.service.TrainerService;
import com.company.gum.model.service.impl.ClientServiceImpl;
import com.company.gum.model.service.impl.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class ShowUserProfileCommand.
 *
 * @author Vladislav Kuzmich
 */
public class ShowUserProfileCommand implements Command {

	/**
	 * The Constant logger.
	 */
	private static final Logger logger = LogManager.getLogger();

	/**
	 * The client service.
	 */
	private final ClientService clientService = ClientServiceImpl.getInstance();

	/**
	 * The trainer service.
	 */
	private final TrainerService trainerService = TrainerServiceImpl.getInstance();

	/**
	 * Execute.
	 *
	 * @param requestContent the request content
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		Router router;
		User user;
		try {
			int userId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
			User.UserRole role = User.UserRole
					.valueOf(requestContent.getParameterByName(AttributeName.USER_ROLE).toUpperCase());
			switch (role) {
				case CLIENT:
					user = clientService.findClientById(userId);
					break;
				case TRAINER:
					user = trainerService.findTrainerById(userId);
					break;
				default:
					logger.debug("Debug exception for testing. When adding a user with a new role.");
					throw new CommandException("Debug exception for testing. When adding a user with a new role.");
			}
			requestContent.putAttribute(AttributeName.USER, user);
			router = new Router(PagePath.USER_PROFILE, FORWARD);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}