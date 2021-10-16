package com.company.gum.controller.command.impl;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.User;
import com.company.gum.model.service.UserService;
import com.company.gum.model.service.impl.UserServiceImpl;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class UploadImageCommand.
 *
 * @author Vladislav Kuzmich
 */
public class UploadImageCommand implements Command {

	/**
	 * The Constant userService.
	 */
	private static final UserService userService = UserServiceImpl.getInstance();

	/**
	 * Execute.
	 *
	 * @param requestContent the request content
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		int userId = (Integer) requestContent.getSessionAttributeByName(USER_ID);
		ByteArrayInputStream photo = (ByteArrayInputStream) requestContent.getAttributeByName(USER_PHOTO);
		Router router;
		try {
			User user;
			user = new User();
			user.setId(userId);
			user.setPhoto(photo.readAllBytes());
			userService.updateUserImage(user);
			String base64Image = Base64.getEncoder().encodeToString(user.getPhoto());
			requestContent.putSessionAttribute(USER_PHOTO, IMAGE_SRC_PREFIX + base64Image);
			router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
			if (requestContent.getSessionAttributeByName(CURRENT_PAGE) == null) {
				router = new Router(PagePath.INDEX, FORWARD);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}