package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.dao.CommentDao;
import com.company.gum.model.entity.Comment;
import com.company.gum.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.company.gum.model.dao.TableColumnName.*;

/**
 * The Class CommentDaoImpl.
 *
 * @author Vladislav Kuzmich
 */
public class CommentDaoImpl implements CommentDao {

	/**
	 * The Constant logger.
	 */
	private static final Logger logger = LogManager.getLogger();

	/**
	 * The Constant SQL_CREATE_COMMENT.
	 */
	private static final String SQL_CREATE_COMMENT = "INSERT INTO comments (user_id, comment_text)\n"
			+ "VALUES (?, ?)";

	/**
	 * The Constant SQL_UPDATE_COMMENT.
	 */
	private static final String SQL_UPDATE_COMMENT = "UPDATE comments\n"
			+ "SET comment_text = IFNULL(?, comment_text)\n"
			+ "WHERE comment_id = ?";

	/**
	 * The Constant SQL_DELETE_COMMENT.
	 */
	private static final String SQL_DELETE_COMMENT = "UPDATE comments\n"
			+ "SET is_active = false\n"
			+ "WHERE comment_id = ?";

	/**
	 * The Constant SQL_FIND_ALL_ACTIVE_COMMENT.
	 */
	private static final String SQL_FIND_ALL_ACTIVE_COMMENT = "SELECT comments.comment_id,\n"
			+ "       comments.user_id,\n"
			+ "       u.name    AS user_name,\n"
			+ "       u.surname AS user_surname,\n"
			+ "       u.image,\n"
			+ "       comments.comment_date,\n"
			+ "       comments.comment_text,\n"
			+ "       comments.is_active\n"
			+ "FROM comments\n"
			+ "         LEFT JOIN users u ON comments.user_id = u.user_id\n"
			+ "WHERE comments.is_active = true";

	/**
	 * The instance.
	 */
	private static CommentDaoImpl instance;

	/**
	 * Instantiates a new comment dao impl.
	 */
	private CommentDaoImpl() {
	}

	/**
	 * Gets the single instance of CommentDaoImpl.
	 *
	 * @return single instance of CommentDaoImpl
	 */
	public static CommentDaoImpl getInstance() {
		if (instance == null) {
			instance = new CommentDaoImpl();
		}
		return instance;
	}

	/**
	 * Creates the comment.
	 *
	 * @param comment the comment
	 * @return the comment
	 * @throws DaoException the dao exception
	 */
	@Override
	public Comment createComment(Comment comment) throws DaoException {
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_CREATE_COMMENT,
				     Statement.RETURN_GENERATED_KEYS)) {
			statement.setInt(1, comment.getUserId());
			statement.setString(2, comment.getCommentText());

			statement.execute();

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				int commentId = resultSet.getInt(1);
				comment.setId(commentId);
				logger.debug("Comment with id {} was created", comment.getId());
			} else {
				logger.debug("Comment with id {} was not created", comment.getId());
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return comment;
	}

	/**
	 * Update comment.
	 *
	 * @param commentId   the comment id
	 * @param commentText the comment text
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean updateComment(int commentId, String commentText) throws DaoException {
		boolean isUpdated;

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMMENT)) {
			if (commentText != null) {
				statement.setString(1, commentText);
			} else {
				statement.setNull(1, Types.VARCHAR);
			}
			statement.setInt(2, commentId);

			isUpdated = statement.execute();

			logger.debug(isUpdated ? "Comment {} was updated"
					: "Comment {} was not updated", commentId);

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isUpdated;
	}

	/**
	 * Delete comment.
	 *
	 * @param commentId the comment id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean deleteComment(int commentId) throws DaoException {
		boolean isDeleted;

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COMMENT)) {
			statement.setInt(1, commentId);
			isDeleted = statement.executeUpdate() == 1;
			logger.debug(isDeleted ? "Comment with id {} has been deleted"
					: "Can't delete comment with id {}", commentId);

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isDeleted;
	}

	/**
	 * Find all active comment.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Comment> findAllActiveComment() throws DaoException {
		List<Comment> comments = new ArrayList<>();

		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		     PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACTIVE_COMMENT)) {

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Comment comment = getCommentFromResultSet(resultSet);
				comments.add(comment);
			}

			logger.debug("Found {} active comments", comments.size());

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return comments;
	}

	/**
	 * Gets the comment from result set.
	 *
	 * @param resultSet the result set
	 * @return the comment from result set
	 * @throws SQLException the SQL exception
	 */
	private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
		return new Comment.Builder()
				.id(resultSet.getInt(COMMENT_ID))
				.userId(resultSet.getInt(USER_ID))
				.userName(resultSet.getString(USER_NAME_COMMENT))
				.userSurname(resultSet.getString(USER_SURNAME_COMMENT))
				.photo(resultSet.getBytes(PHOTO))
				.commentText(resultSet.getString(COMMENT_TEXT))
				.commentDate(resultSet.getTimestamp(COMMENT_DATE).toLocalDateTime())
				.active(resultSet.getBoolean(IS_ACTIVE))
				.base64Image(resultSet.getBytes(PHOTO) != null
						? IMAGE_SRC_PREFIX + Base64.getEncoder().encodeToString(resultSet.getBytes(PHOTO))
						: DEFAULT_IMAGE)
				.build();
	}
}
