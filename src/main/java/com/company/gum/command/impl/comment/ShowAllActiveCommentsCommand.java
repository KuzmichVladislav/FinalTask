package com.company.gum.command.impl.comment;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Comment;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;
import com.company.gum.service.impl.CommentServiceImpl;

import java.util.Collections;
import java.util.List;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class ShowAllActiveCommentsCommand implements Command {

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        List<Comment> comments;
        try { // TODO: 9/20/2021 pagination
            int pageNumber = Integer.parseInt(requestContent.getParameterByName(AttributeName.PAGE));
            comments = commentService.findAllActiveComment();
            int commentsCount = comments.size();
            Collections.reverse(comments);
            int numberOfPages = (int) Math.ceil((commentsCount) / 5d);
            List<Comment> showComments = comments.subList((pageNumber - 1) * 5, Math.min(pageNumber * 5, commentsCount));
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
