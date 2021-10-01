package com.company.gum.model.service;

import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Comment;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommentService.
 */
public interface CommentService {

    /**
     * Creates the comment.
     *
     * @param comment the comment
     * @return the comment
     * @throws ServiceException the service exception
     */
    Comment createComment(Comment comment) throws ServiceException;

    /**
     * Update comment.
     *
     * @param commentId the comment id
     * @param commentText the comment text
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean updateComment(int commentId, String commentText) throws ServiceException;

    /**
     * Delete comment.
     *
     * @param commentId the comment id
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean deleteComment(int commentId) throws ServiceException;

    /**
     * Find all active comment.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAllActiveComment() throws ServiceException;
}
