package com.company.gum.command.impl.comment;

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

import static com.company.gum.command.AttributeName.COMMENTS;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class ShowAllActiveCommentsCommand implements Command {

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        List<Comment> comments;
        try { // TODO: 9/20/2021 pagination
//            Integer pageNumber = Integer.parseInt(requestContent.getParameterByName(AttributeName.PAGE));
//            Integer numberOsComments = commentService.commentCount(true);
            comments = commentService.findAllActiveComment();
            Collections.reverse(comments);
//            int numberOfPages = new BigDecimal(numberOsComments).divide(new BigDecimal(5), MathContext.DECIMAL32).setScale(0, RoundingMode.UP).intValue();
//            requestContent.putAttribute(AttributeName.NUMBER_OF_PAGES, numberOfPages);
//            requestContent.putAttribute(AttributeName.CURRENT_NUMBER_PAGE, pageNumber);
            requestContent.putAttribute(COMMENTS, comments);
            requestContent.putAttribute(COMMENTS, comments);
            router = new Router(PagePath.COMMENTS, FORWARD);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
