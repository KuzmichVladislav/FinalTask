package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;

import static com.company.gum.command.AttributeName.USER_ID;
import static com.company.gum.command.AttributeName.VERIFICATION;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class VerificationCommand implements Command {

	private ClientService clientService = ClientServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		Router router;

		int clientId = Integer.parseInt(requestContent.getParameterByName(USER_ID));

		try {
			boolean isVerified = clientService.verification(clientId);
			requestContent.putAttribute(VERIFICATION, isVerified);
			router = new Router(PagePath.VERIFICATION, FORWARD);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
