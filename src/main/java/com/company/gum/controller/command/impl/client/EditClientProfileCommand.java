package com.company.gum.controller.command.impl.client;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Client;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.service.impl.ClientServiceImpl;
import com.company.gum.model.validator.FormValidator;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * The Class EditClientProfileCommand.
 *
 * @author Vladislav Kuzmich
 */
public class EditClientProfileCommand implements Command {

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
		Router router;
		FormValidator validator = FormValidator.getInstance();
		boolean isValid = true;
		try {
			int clientId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
			String userName = requestContent.getParameterByName(USER_NAME).isBlank()
					? (String) requestContent.getSessionAttributeByName(USER_NAME)
					: requestContent.getParameterByName(USER_NAME).strip();
			String userSurname = requestContent.getParameterByName(USER_SURNAME).isBlank()
					? (String) requestContent.getSessionAttributeByName(USER_SURNAME)
					: requestContent.getParameterByName(USER_SURNAME).strip();
			String userPhone = requestContent.getParameterByName(USER_PHONE).isBlank()
					? (String) requestContent.getSessionAttributeByName(USER_PHONE)
					: requestContent.getParameterByName(USER_PHONE).strip();
			String userMail = requestContent.getParameterByName(USER_MAIL).isBlank()
					? (String) requestContent.getSessionAttributeByName(USER_MAIL)
					: requestContent.getParameterByName(USER_MAIL).strip();
			if (!validator.checkNameSurname(userName)) {
				isValid = false;
				requestContent.putAttribute(ERROR_MESSAGE, "invalid.name");
			}
			if (!validator.checkNameSurname(userSurname) && isValid) {
				isValid = false;
				requestContent.putAttribute(ERROR_MESSAGE, "invalid.surname");
			}
			if (!validator.checkPhone(userPhone) && isValid) {
				isValid = false;
				requestContent.putAttribute(ERROR_MESSAGE, "invalid.phone");
			}
			if (!validator.checkMail(userMail) && isValid) {
				isValid = false;
				requestContent.putAttribute(ERROR_MESSAGE, "invalid.email");
			}
			if (isValid) {
				Client client = new Client.Builder()
						.id(clientId)
						.name(userName)
						.surname(userSurname)
						.mail(userMail)
						.phone(userPhone)
						.build();
				clientService.editClient(client);
				client = clientService.findClientById(clientId);
				requestContent.putSessionAttribute(USER_NAME, client.getName());
				requestContent.putSessionAttribute(USER_SURNAME, client.getSurname());
				requestContent.putSessionAttribute(USER_PHONE, client.getPhone());
				requestContent.putSessionAttribute(USER_MAIL, client.getMail());
				router = new Router(PagePath.CLIENT_PROFILE, REDIRECT);
			} else {
				router = new Router(PagePath.CLIENT_PROFILE, FORWARD);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}