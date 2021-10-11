package com.company.gum.model.service.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.dao.CommentDao;
import com.company.gum.model.dao.impl.CommentDaoImpl;
import com.company.gum.model.entity.Comment;
import com.company.gum.model.service.CommentService;

import java.util.List;

/**
 * The Class CommentServiceImpl.
 *
 * @author Vladislav Kuzmich
 */
public class CommentServiceImpl implements CommentService {

	/**
	 * The instance.
	 */
	private static CommentServiceImpl instance;

	/**
	 * The comment dao.
	 */
	private final CommentDao commentDao = CommentDaoImpl.getInstance();

	/**
	 * Instantiates a new comment service impl.
	 */
	private CommentServiceImpl() {
	}

	/**
	 * Gets the single instance of CommentServiceImpl.
	 *
	 * @return single instance of CommentServiceImpl
	 */
	public static CommentServiceImpl getInstance() {
		if (instance == null) {
			instance = new CommentServiceImpl();
		}
		return instance;
	}

	/**
	 * Creates the comment.
	 *
	 * @param comment the comment
	 * @return the comment
	 * @throws ServiceException the service exception
	 */
	@Override
	public Comment createComment(Comment comment) throws ServiceException {
		Comment createdComment;
		try {
			createdComment = commentDao.createComment(comment);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return createdComment;
	}

	/**
	 * Update comment.
	 *
	 * @param commentId   the comment id
	 * @param commentText the comment text
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean updateComment(int commentId, String commentText) throws ServiceException {
		boolean isUpdated;
		try {
			isUpdated = commentDao.updateComment(commentId, commentText);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isUpdated;
	}

	/**
	 * Delete comment.
	 *
	 * @param commentId the comment id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean deleteComment(int commentId) throws ServiceException {
		boolean isDeleted;
		try {
			isDeleted = commentDao.deleteComment(commentId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isDeleted;
	}

	/**
	 * Find all active comment.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<Comment> findAllActiveComment() throws ServiceException {
		List<Comment> activeComments;
		try {
			activeComments = commentDao.findAllActiveComment();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return activeComments;
	}
}
