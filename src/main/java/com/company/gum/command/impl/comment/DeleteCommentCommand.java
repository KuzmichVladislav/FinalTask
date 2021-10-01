package com.company.gum.command.impl.comment;

import com.company.gum.command.Command;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;
import com.company.gum.service.impl.CommentServiceImpl;

import static com.company.gum.command.AttributeName.COMMENT_ID;
import static com.company.gum.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class DeleteCommentCommand implements Command {

    private final CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int commentId = Integer.parseInt(requestContent.getParameterByName(COMMENT_ID));
            commentService.deleteComment(commentId);
            router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
