package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * The Class CommentDaoImplTest.
 *
 * @author Vladislav Kuzmich
 */
class CommentDaoImplTest {

    /**
     * The instance.
     */
    @Mock
    CommentDaoImpl instance;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of CreateComment method, of class CommentDaoImpl.
     */
    @Test
    void testCreateComment() {
        try {
            when(instance.createComment(any())).thenReturn(new Comment());
            Comment result = instance.createComment(new Comment());
            Assertions.assertEquals(new Comment(), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of updateComment method, of class CommentDaoImpl.
     */
    @Test
    void testUpdateComment() {
        try {
            when(instance.updateComment(anyInt(), anyString())).thenReturn(true);
            boolean result = instance.updateComment(10, "test");
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of deleteComment method, of class CommentDaoImpl.
     */
    @Test
    void testDeleteComment() {
        try {
            when(instance.deleteComment(anyInt())).thenReturn(true);
            boolean result = instance.deleteComment(10);
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findAllActiveComment method, of class CommentDaoImpl.
     */
    @Test
    void testFindAllActiveComment() {
        try {
            when(instance.findAllActiveComment()).thenReturn(Collections.singletonList(new Comment()));
            List<Comment> result = instance.findAllActiveComment();
            Assertions.assertEquals(Collections.singletonList(new Comment()), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }
}