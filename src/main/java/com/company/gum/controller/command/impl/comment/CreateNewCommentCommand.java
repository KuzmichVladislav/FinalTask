package com.company.gum.controller.command.impl.comment;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Comment;
import com.company.gum.model.service.CommentService;
import com.company.gum.model.service.impl.CommentServiceImpl;
import com.company.gum.model.util.XssDefender;

import static com.company.gum.controller.command.AttributeName.COMMENT;
import static com.company.gum.controller.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * The Class CreateNewCommentCommand.
 *
 * @author Vladislav Kuzmich
 */
public class CreateNewCommentCommand implements Command {

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
			int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
			String commentText = XssDefender.getInstance().getStringFromDescription(requestContent, COMMENT);
			Comment comment = new Comment.Builder().build();
			comment.setUserId(userId);
			comment.setCommentText(commentText);
			commentService.createComment(comment);
			router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), REDIRECT);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
