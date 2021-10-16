package com.company.gum.controller.command.impl.comment;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.CommentService;
import com.company.gum.model.service.impl.CommentServiceImpl;
import com.company.gum.model.util.XssDefender;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class EditCommentCommand.
 *
 * @author Vladislav Kuzmich
 */
public class EditCommentCommand implements Command {

	/**
	 * The comment service.
	 */
	private final CommentService commentService = CommentServiceImpl.getInstance();

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
			int commentId = Integer.parseInt(requestContent.getParameterByName(COMMENT_ID));
			String commentText = XssDefender.getInstance().getStringFromDescription(requestContent, COMMENT);
			commentService.updateComment(commentId, commentText);
			router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}