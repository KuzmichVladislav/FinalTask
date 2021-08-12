package com.company.gum.dao.impl;

import com.company.gum.dao.CommentDao;
import com.company.gum.entity.Comment;
import com.company.gum.exception.DaoException;
import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.gum.dao.TableColumnName.*;

public class CommentDaoImpl implements CommentDao {

    private static final Logger logger = LogManager.getLogger();

    private static final String CREATE_COMMENT = "INSERT INTO comments (user_id, comment_text) VALUES (?, ?)";
    private static final String UPDATE_COMMENT = "UPDATE comments SET user_id = IFNULL(?, user_id), comment_text = IFNULL(?, comment_text), is_active = IFNULL(?, is_active) WHERE comment_id = ?";
    private static final String DELETE_COMMENT = "UPDATE comments SET is_active = false WHERE comment_id = ?";
    private static final String FIND_COMMENT = "SELECT comments.comment_id,\n"
            + "       comments.user_id,\n"
            + "       u.name    AS name,\n"
            + "       u.surname AS surname,\n"
            + "       u.profile_image,\n"
            + "       comments.comment_date,\n"
            + "       comments.comment_text,\n"
            + "       comments.is_active\n"
            + "FROM comments\n"
            + "         LEFT JOIN users u ON comments.user_id = u.user_id\n"
            + "WHERE comments.comment_id = ?";

    private static final String FIND_ALL_COMMENT = "SELECT comments.comment_id,\n"
            + "       comments.user_id,\n"
            + "       u.name    AS user_name,\n"
            + "       u.surname AS user_surname,\n"
            + "       u.profile_image,\n"
            + "       comments.comment_date,\n"
            + "       comments.comment_text,\n"
            + "       comments.is_active\n"
            + "FROM comments\n"
            + "         LEFT JOIN users u ON comments.user_id = u.user_id";
    private static CommentDao commentDao = new CommentDaoImpl();

    private static final String FIND_ALL_ACTIVE_COMMENT = "SELECT comments.comment_id,\n"
            + "       comments.user_id,\n"
            + "       u.name    AS user_name,\n"
            + "       u.surname AS user_surname,\n"
            + "       u.profile_image,\n"
            + "       comments.comment_date,\n"
            + "       comments.comment_text,\n"
            + "       comments.is_active\n"
            + "FROM comments\n"
            + "         LEFT JOIN users u ON comments.user_id = u.user_id\n"
            + "WHERE comments.is_active = true";

    private static final String FIND_ALL_COMMENT_WITH_FILTER = "SELECT comments.comment_id,\n"
            + "    comments.user_id,\n"
            + "    u.name    AS user_name,\n"
            + "    u.surname AS user_surname,\n"
            + "    u.profile_image,\n"
            + "    comments.comment_date,\n"
            + "    comments.comment_text,\n"
            + "    comments.is_active\n"
            + "FROM comments\n"
            + "    LEFT JOIN users u ON comments.user_id = u.user_id\n"
            + "WHERE u.name = IFNULL(?, u.name)\n"
            + "  AND u.surname = IFNULL(?, u.surname)\n"
            + "  AND CAST(comments.comment_date AS date) = IFNULL(?, CAST(comments.comment_date AS date))\n"
            + "  AND comments.is_active = IFNULL(?, comments.is_active)";

    private static final String COMMENT_COUNT = "SELECT COUNT(comments.comment_id)\n"
            + "FROM comments\n"
            + "WHERE is_active = IFNULL(?, is_active)";

    private CommentDaoImpl() {
    }

    public static CommentDao getInstance() {
        return commentDao;
    }

    @Override
    public Comment createComment(Comment comment) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(CREATE_COMMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, comment.getUserId());
            statement.setString(2, comment.getCommentText());

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int commentId = resultSet.getInt(1);
                comment.setId(commentId);
            }
            logger.debug("Comment {} was created", comment);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comment;
    }

    @Override
    public boolean updateComment(Comment comment) throws DaoException {
        boolean isUpdated;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_COMMENT)) {
            if (comment.getUserId() != null) {
                statement.setInt(1, comment.getUserId());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            if (comment.getCommentText() != null) {
                statement.setString(2, comment.getCommentText());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (comment.getActive() != null) {
                statement.setBoolean(3, comment.getActive());
            } else {
                statement.setNull(3, Types.BOOLEAN);
            }
            statement.setInt(4, comment.getId());

            isUpdated = statement.execute();
            logger.debug("Comment was {} updated", comment);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteComment(int commentId) throws DaoException {
        boolean isDeleted;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT)) {
            statement.setInt(1, commentId);
            isDeleted = statement.executeUpdate() == 1;

            if (isDeleted) {
                logger.debug("Comment with id \"{}\" has been deleted", commentId);
            } else {
                logger.debug("Can't delete comment with id \"{}\"", commentId);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public Comment findComment(int commentId) throws DaoException {
        Comment comment = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_COMMENT)) {
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                comment = getCommentFromResultSet(resultSet);
                logger.debug("Comment with id \"{}\" was found: {}", comment.getId(), comment);
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
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_COMMENT)) {

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
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_COMMENT)) {

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

    @Override
    public List<Comment> findCommentWithFilter(Comment filter) throws DaoException {
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_ALL_COMMENT_WITH_FILTER)) {
            if (filter.getUserName() != null) {
                statement.setString(1, filter.getUserName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }
            if (filter.getUserSurname() != null) {
                statement.setString(2, filter.getUserSurname());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }
            if (filter.getCommentDate() != null) {
                statement.setDate(3, Date.valueOf(filter.getCommentDate().toLocalDate()));
            } else {
                statement.setNull(3, Types.DATE);
            }
            if (filter.getActive() != null) {
                statement.setBoolean(4, filter.getActive());
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = getCommentFromResultSet(resultSet);
                comments.add(comment);
            }
            logger.debug("Found {} comments with filter: {}", comments.size(), comments);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return comments;
    }

    @Override
    public int commentCount(Boolean active) throws DaoException {
        int count = 0;

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
                PreparedStatement statement = connection.prepareStatement(COMMENT_COUNT)) {

            if (active != null) {
                statement.setBoolean(1, active);
            } else {
                statement.setNull(1, Types.BOOLEAN);
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
                if (active) {
                    logger.debug("Count of active comments  {}", count);
                } else {
                    logger.debug("Count of inactive comments  {}", count);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }

    private Comment getCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getInt(COMMENT_ID));
        comment.setUserId(resultSet.getInt(USER_ID));
        comment.setUserName(resultSet.getString(USER_NAME));
        comment.setUserSurname(resultSet.getString(USER_SURNAME));
        comment.setProfileImage(resultSet.getString(PROFILE_IMAGE));
        comment.setCommentDate(resultSet.getTimestamp(COMMENT_DATE).toLocalDateTime());
        comment.setCommentText(resultSet.getString(COMMENT_TEXT));
        comment.setActive(resultSet.getBoolean(IS_ACTIVE));
        return comment;
    }
}
