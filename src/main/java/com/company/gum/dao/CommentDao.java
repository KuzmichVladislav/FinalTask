package com.company.gum.dao;

import com.company.gum.entity.Comment;
import com.company.gum.exception.DaoException;

import java.util.List;

public interface CommentDao {

	Comment createComment(Comment comment) throws DaoException;

	boolean updateComment(Comment comment) throws DaoException;

	boolean deleteComment(int commentId) throws DaoException;

	Comment findComment(int commentId) throws DaoException;

	List<Comment> findAllComment() throws DaoException;

	List<Comment> findAllActiveComment() throws DaoException;

	List<Comment> findCommentWithFilter(Comment filter) throws DaoException;

	int commentCount(Boolean active) throws DaoException;

}
