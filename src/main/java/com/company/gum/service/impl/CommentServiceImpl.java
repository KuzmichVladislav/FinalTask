package com.company.gum.service.impl;

import com.company.gum.dao.CommentDao;
import com.company.gum.dao.impl.CommentDaoImpl;
import com.company.gum.entity.Comment;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    public CommentDao commentDao = CommentDaoImpl.getInstance();
    private static CommentServiceImpl instance;

    private CommentServiceImpl() {
    }

    public static CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    @Override
    public Comment createComment(Comment comment) throws ServiceException {
        Comment createdComment;
        try{
            createdComment = commentDao.createComment(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdComment;
    }

    @Override
    public boolean updateComment(Comment comment) throws ServiceException {
        return false;
    }

    @Override
    public boolean deleteComment(int commentId) throws ServiceException {
        return false;
    }

    @Override
    public Comment findComment(int commentId) throws ServiceException {
        return null;
    }

    @Override
    public List<Comment> findAllComment() throws ServiceException {
        return null;
    }

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

    @Override
    public List<Comment> findCommentWithFilter(Comment filter) throws ServiceException {
        return null;
    }

    @Override
    public int commentCount(Boolean active) throws ServiceException {
        return 0;
    }
}
