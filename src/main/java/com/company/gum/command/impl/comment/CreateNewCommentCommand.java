package com.company.gum.command.impl.comment;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Comment;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;
import com.company.gum.service.impl.CommentServiceImpl;

import static com.company.gum.command.AttributeName.COMMENT;
import static com.company.gum.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class CreateNewCommentCommand implements Command {

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {

            int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            String commentText = requestContent.getParameterByName(COMMENT).strip().replaceAll("<", "").replaceAll(">",
                    "");

            Comment comment = new Comment.Builder().build();
            comment.setUserId(userId);
            comment.setCommentText(commentText);

            commentService.createComment(comment);
            router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
