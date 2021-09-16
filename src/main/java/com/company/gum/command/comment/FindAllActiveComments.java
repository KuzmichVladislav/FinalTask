package com.company.gum.command.comment;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Comment;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;
import com.company.gum.service.impl.CommentServiceImpl;

import java.util.List;

public class FindAllActiveComments implements Command {
    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        List<Comment> comments;
        try {
//            Integer pageNumber = Integer.parseInt(requestContent.getParameterByName(AttributeName.PAGE));
//            Integer numberOsComments = commentService.commentCount(true);
            comments = commentService.findAllActiveComment();
//            int numberOfPages = new BigDecimal(numberOsComments).divide(new BigDecimal(5), MathContext.DECIMAL32).setScale(0, RoundingMode.UP).intValue();
//            requestContent.putAttribute(AttributeName.NUMBER_OF_PAGES, numberOfPages);
//            requestContent.putAttribute(AttributeName.CURRENT_NUMBER_PAGE, pageNumber);
            requestContent.putAttribute(AttributeName.COMMENTS, comments);
            requestContent.putAttribute(AttributeName.COMMENTS, comments);
            page = PagePath.COMMENTS;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
