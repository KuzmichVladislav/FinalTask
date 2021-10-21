package com.company.gum.controller.command.impl;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Client;
import com.company.gum.model.entity.Trainer;
import com.company.gum.model.entity.User;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.service.TrainerService;
import com.company.gum.model.service.UserService;
import com.company.gum.model.service.impl.ClientServiceImpl;
import com.company.gum.model.service.impl.TrainerServiceImpl;
import com.company.gum.model.service.impl.UserServiceImpl;
import com.company.gum.model.validator.FormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * The Class LoginCommand.
 *
 * @author Vladislav Kuzmich
 */
public class LoginCommand implements Command {

	/**
	 * The Constant logger.
	 */
	private static final Logger logger = LogManager.getLogger();

	/**
	 * The user service.
	 */
	private final UserService userService = UserServiceImpl.getInstance();

	/**
	 * The trainer service.
	 */
	private final TrainerService trainerService = TrainerServiceImpl.getInstance();

	/**
	 * The client service.
	 */
	private final ClientService clientService = ClientServiceImpl.getInstance();

	/**
	 * Execute.
	 *
	 * @param requestContent the request content
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		FormValidator validator = FormValidator.getInstance();
		String login = requestContent.getParameterByName(USER_LOGIN);
		String password = requestContent.getParameterByName(USER_PASSWORD);
		User user;
		Router router;
		try {
			if (validator.checkLogin(login) && validator.checkPassword(password)) {
				user = userService.findUserByLoginAndPassword(login, password);
				if (user != null) {
					if (user.isVerification()) {
						router = getRouter(requestContent, user);
						requestContent.putSessionAttribute(USER_AUTHORIZATION, true);
					} else {
						requestContent.putAttribute(ERROR_MESSAGE, "verification.error");
						router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
					}
				} else {
					requestContent.putAttribute(ERROR_MESSAGE, "wrong.login.or.password");
					router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
				}
			} else {
				requestContent.putAttribute(ERROR_MESSAGE, "login.or.password.is.not.valid");
				router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}

	/**
	 * Gets the router.
	 *
	 * @param requestContent the request content
	 * @param user           the user
	 * @return the router
	 * @throws ServiceException the service exception
	 * @throws CommandException the command exception
	 */
	private Router getRouter(SessionRequestContent requestContent, User user) throws ServiceException, CommandException {
		Router router;
		createUser(requestContent, user);
		switch (user.getRole()) {
			case ADMIN:
				router = new Router(PagePath.WELCOME, REDIRECT);
				break;
			case TRAINER:
				createTrainer(requestContent, user);
				router = new Router(PagePath.WELCOME, REDIRECT);
				break;
			case CLIENT:
				Client client = clientService.findClientById(user.getId());
				createClient(requestContent, client);
				router = new Router(PagePath.WELCOME, REDIRECT);
				break;
			default:
				logger.debug("Debug exception for testing. When adding a user with a new role.");
				throw new CommandException("Debug exception for testing. When adding a user with a new role.");
		}
		return router;
	}

	/**
	 * Creates the client.
	 *
	 * @param requestContent the request content
	 * @param client         the client
	 */
	private void createClient(SessionRequestContent requestContent, Client client) {
		requestContent.putSessionAttribute(USER_REGISTER_DATE, client.getRegisterDate());
		requestContent.putSessionAttribute(USER_PHONE, client.getPhone());
		requestContent.putSessionAttribute(USER_MONEY, client.getMoney());
		requestContent.putSessionAttribute(USER_DISCOUNT, client.getDiscount());
	}

	/**
	 * Creates the trainer.
	 *
	 * @param requestContent the request content
	 * @param user           the user
	 * @throws ServiceException the service exception
	 */
	private void createTrainer(SessionRequestContent requestContent, User user) throws ServiceException {
		Trainer trainer = trainerService.findTrainerById(user.getId());
		requestContent.putSessionAttribute(USER_REGISTER_DATE, trainer.getRegisterDate());
		requestContent.putSessionAttribute(USER_PHONE, trainer.getPhone());
		requestContent.putSessionAttribute(DESCRIPTION, trainer.getDescription());
		requestContent.putSessionAttribute(EXPERIENCE, trainer.getExperience());
	}

	/**
	 * Creates the user.
	 *
	 * @param requestContent the request content
	 * @param user           the user
	 */
	private void createUser(SessionRequestContent requestContent, User user) {
		requestContent.putSessionAttribute(USER_ID, user.getId());
		requestContent.putSessionAttribute(USER_LOGIN, user.getLogin());
		requestContent.putSessionAttribute(USER_ROLE, user.getRole().name());
		requestContent.putSessionAttribute(USER_NAME, user.getName());
		requestContent.putSessionAttribute(USER_SURNAME, user.getSurname());
		requestContent.putSessionAttribute(USER_MAIL, user.getMail());
		requestContent.putSessionAttribute(USER_PHOTO, user.getBase64Image());
	}
}