package com.company.gum.command.impl.comment;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;
import com.company.gum.service.impl.CommentServiceImpl;

import static com.company.gum.command.AttributeName.COMMENT;
import static com.company.gum.command.AttributeName.COMMENT_ID;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class EditCommentCommand implements Command {

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int commentId = Integer.parseInt(requestContent.getParameterByName(COMMENT_ID));
            String commentText = requestContent.getParameterByName(COMMENT);
            commentService.updateComment(commentId, commentText);
            requestContent.putAttribute(COMMENT_ID, commentId);
            requestContent.putAttribute(COMMENT, commentText);
            router = new Router(PagePath.COMMENT_EDITED, REDIRECT);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}