package com.company.gum.command.impl.admin;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.AdminService;
import com.company.gum.service.impl.AdminServiceImpl;

import static com.company.gum.command.AttributeName.USER_ID;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class DeleteUserCommand implements Command {

	AdminService adminService = AdminServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		Router router;
		try {
			int userId = Integer.parseInt(requestContent.getParameterByName(USER_ID));
			adminService.deleteUser(userId);
			router = new Router(PagePath.USER_DELETED, REDIRECT);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
