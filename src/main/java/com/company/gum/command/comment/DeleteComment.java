package com.company.gum.command.comment;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;
import com.company.gum.service.impl.CommentServiceImpl;

public class DeleteComment implements Command {
    CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Integer commentId = Integer.parseInt(requestContent.getParameterByName(AttributeName.COMMENT_ID));
            commentService.deleteComment(commentId);
            requestContent.putAttribute(AttributeName.COMMENT_ID, commentId);
            page = PagePath.ORDER_DELETED_BY_CLIENT; // TODO: 9/16/2021  
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
