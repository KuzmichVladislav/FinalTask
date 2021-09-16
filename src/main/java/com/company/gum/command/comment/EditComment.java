package com.company.gum.command.comment;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;
import com.company.gum.service.impl.CommentServiceImpl;

public class EditComment implements Command {

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try{
            int commentId = Integer.parseInt(requestContent.getParameterByName(AttributeName.COMMENT_ID));
            String commentText = requestContent.getParameterByName(AttributeName.COMMENT);
            commentService.updateComment(commentId, commentText);
            requestContent.putAttribute(AttributeName.COMMENT_ID, commentId);
            requestContent.putAttribute(AttributeName.COMMENT, commentText);
            page = PagePath.COMMENT_EDITED;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
