package com.company.gum.controller.command.impl.comment;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Comment;
import com.company.gum.model.service.CommentService;
import com.company.gum.model.service.impl.CommentServiceImpl;

import java.util.Collections;
import java.util.List;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class ShowAllActiveCommentsCommand.
 *
 * @author Vladislav Kuzmich
 */
public class ShowAllActiveCommentsCommand implements Command {

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
		List<Comment> comments;
		try {
			int pageNumber = Integer.parseInt(requestContent.getParameterByName(AttributeName.PAGE));
			comments = commentService.findAllActiveComment();
			int commentsCount = comments.size();
			Collections.reverse(comments);
			int numberOfPages = (int) Math.ceil((commentsCount) / 5d);
			List<Comment> showComments = comments.subList((pageNumber - 1) * 5,
					Math.min(pageNumber * 5, commentsCount));
			requestContent.putAttribute(NUMBER_OF_PAGES, numberOfPages);
			requestContent.putAttribute(CURRENT_NUMBER_PAGE, pageNumber);
			requestContent.putAttribute(COMMENTS, showComments);
			router = new Router(PagePath.COMMENTS, FORWARD);

		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
