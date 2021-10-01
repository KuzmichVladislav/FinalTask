package com.company.gum.dao.impl;

import com.company.gum.dao.CommentDao;
import com.company.gum.entity.Comment;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.company.gum.dao.TableColumnName.*;

public class CommentDaoImpl implements CommentDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_CREATE_COMMENT = "INSERT INTO comments (user_id, comment_text)\n" + "VALUES (?, ?)";
    private static final String SQL_UPDATE_COMMENT = "UPDATE comments\n"
            + "SET comment_text = IFNULL(?, comment_text)\n" + "WHERE comment_id = ?";
    private static final String SQL_DELETE_COMMENT = "UPDATE comments\n" + "SET is_active = false\n"
            + "WHERE comment_id = ?";
    private static final String SQL_FIND_COMMENT = "SELECT comments.comment_id,\n" + "       comments.user_id,\n"
            + "       u.name    AS name,\n" + "       u.surname AS surname,\n" + "       u.image,\n"
            + "       comments.comment_date,\n" + "       comments.comment_text,\n" + "       comments.is_active\n"
            + "FROM comments\n" + "         LEFT JOIN users u ON comments.user_id = u.user_id\n"
            + "WHERE comments.comment_id = ?";

    private static final String SQL_FIND_ALL_COMMENT = "SELECT comments.comment_id,\n" + "       comments.user_id,\n"
            + "       u.name    AS user_name,\n" + "       u.surname AS user_surname,\n" + "       u.image,\n"
            + "       comments.comment_date,\n" + "       comments.comment_text,\n" + "       comments.is_active\n"
            + "FROM comments\n" + "         LEFT JOIN users u ON comments.user_id = u.user_id";

    private static final String SQL_FIND_ALL_ACTIVE_COMMENT = "SELECT comments.comment_id,\n"
            + "       comments.user_id,\n"
            + "       u.name    AS user_name,\n"
            + "       u.surname AS user_surname,\n"
            + "       u.image,\n" + "       comments.comment_date,\n"
            + "       comments.comment_text,\n"
            + "       comments.is_active\n"
            + "FROM comments\n"
            + "         LEFT JOIN users u ON comments.user_id = u.user_id\n"
            + "WHERE comments.is_active = true";

    private static CommentDaoImpl instance;

    private CommentDaoImpl() {
    }

    public static CommentDaoImpl getInstance() {
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }

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
                logger.debug("Comment {} was created", comment);
            } else {
                logger.debug("Comment {} was not created", comment);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comment;
    }

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

            logger.debug(
                    isUpdated ? "Comment " + commentId + " was updated" : "Comment " + commentId + " was not updated");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteComment(int commentId) throws DaoException {
        boolean isDeleted;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COMMENT)) {
            statement.setInt(1, commentId);
            isDeleted = statement.executeUpdate() == 1;
            logger.debug(isDeleted ? "Comment with id " + commentId + " has been deleted"
                    : "Can't delete comment with id " + commentId);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public Comment findComment(int commentId) throws DaoException {
        Comment comment = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_COMMENT)) {
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                comment = getCommentFromResultSet(resultSet);
                logger.debug("Comment with id \"{}\" was found: {}", comment.getId(), comment);
            } else {
                logger.debug("Comment with id \"{}\" was not found: {}", commentId, comment);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comment;
    }

    @Override
    public List<Comment> findAllComment() throws DaoException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_COMMENT)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }

            logger.debug("Found {} comments: {}", comments.size(), comments);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

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

            logger.debug("Found {} active comments: {}", comments.size(), comments);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Comment.Builder().id(resultSet.getInt(COMMENT_ID)).userId(resultSet.getInt(USER_ID))
                .userName(resultSet.getString(USER_NAME_COMMENT)).userSurname(resultSet.getString(USER_SURNAME_COMMENT))
                .photo(resultSet.getBytes(PHOTO)).commentText(resultSet.getString(COMMENT_TEXT))
                .commentDate(resultSet.getTimestamp(COMMENT_DATE).toLocalDateTime())
                .active(resultSet.getBoolean(IS_ACTIVE))
                .base64Image(resultSet.getBytes(PHOTO) != null
                        ? "data:image/jpg;base64," + Base64.getEncoder().encodeToString(resultSet.getBytes(PHOTO))
                        : "https://i.ibb.co/mDTmCZn/png-transparent-computer-icons-user-profile-user-avatar-blue-heroes-electric-blue.png") // FIXME:
                // 9/21/2021
                .build();
    }
}
