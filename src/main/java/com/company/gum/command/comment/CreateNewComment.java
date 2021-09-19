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

public class CreateNewComment implements Command {

    private CommentService commentService = CommentServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {

            Integer userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            String commentText = requestContent.getParameterByName(AttributeName.COMMENT).strip().replaceAll("<", "").replaceAll(">", "");

            Comment comment = new Comment.Builder().build();
            comment.setUserId(userId);
            comment.setCommentText(commentText);

            commentService.createComment(comment);
            System.out.println(comment);
            page = PagePath.COMMENT_CREATED;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
