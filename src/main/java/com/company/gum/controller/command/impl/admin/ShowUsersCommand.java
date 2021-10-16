package com.company.gum.controller.command.impl.admin;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.User;
import com.company.gum.model.service.UserService;
import com.company.gum.model.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class ShowUsersCommand.
 *
 * @author Vladislav Kuzmich
 */
public class ShowUsersCommand implements Command {

	/**
	 * The user service.
	 */
	private final UserService userService = UserServiceImpl.getInstance();

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
		try {
			List<User> users = new ArrayList<>(userService.findAllUser());
			router = new Router(PagePath.SHOW_ALL_USERS, FORWARD);
			requestContent.putAttribute(AttributeName.USERS, users);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}