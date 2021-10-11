package com.company.gum.model.dao;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Comment;

import java.util.List;

/**
 * The Interface CommentDao.
 *
 * @author Vladislav Kuzmich
 */
public interface CommentDao {

	/**
	 * The image src prefix.
	 */
	String IMAGE_SRC_PREFIX = "data:image/jpg;base64,";

	/**
	 * The default image.
	 */
	String DEFAULT_IMAGE = "https://i.ibb.co/mDTmCZn/png-transparent-computer-icons-user-profile-user-avatar-blue-heroes-electric-blue.png";

	/**
	 * Creates the comment.
	 *
	 * @param comment the comment
	 * @return the comment
	 * @throws DaoException the dao exception
	 */
	Comment createComment(Comment comment) throws DaoException;

	/**
	 * Update comment.
	 *
	 * @param commentId   the comment id
	 * @param commentText the comment text
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean updateComment(int commentId, String commentText) throws DaoException;

	/**
	 * Delete comment.
	 *
	 * @param commentId the comment id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean deleteComment(int commentId) throws DaoException;

	/**
	 * Find all active comment.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	List<Comment> findAllActiveComment() throws DaoException;
}
