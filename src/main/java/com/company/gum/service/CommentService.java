package com.company.gum.service;

import com.company.gum.entity.Comment;
import com.company.gum.exception.ServiceException;

import java.util.List;

public interface CommentService {

	Comment createComment(Comment comment) throws ServiceException;

	boolean updateComment(int commentId, String commentText) throws ServiceException;

	boolean deleteComment(int commentId) throws ServiceException;

	Comment findComment(int commentId) throws ServiceException;

	List<Comment> findAllComment() throws ServiceException;

	List<Comment> findAllActiveComment() throws ServiceException;

	List<Comment> findCommentWithFilter(Comment filter) throws ServiceException;

	int commentCount(Boolean active) throws ServiceException;
}
