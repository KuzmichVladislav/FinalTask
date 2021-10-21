/*
 *
 */
package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * The Class UserDaoImplTest.
 *
 * @author Vladislav Kuzmich
 */
class UserDaoImplTest {

    /**
     * The instance.
     */
    @Mock
    UserDaoImpl instance;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of findUserById method, of class UserDaoImpl.
     */
    @Test
    void testFindUserById() {
        try {
            when(instance.findUserById(anyInt())).thenReturn(new User());
            User result = instance.findUserById(10);
            Assertions.assertEquals(new User(), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findUserByLoginAndPassword method, of class UserDaoImpl.
     */
    @Test
    void testFindUserByLoginAndPassword() {
        try {
            when(instance.findUserByLoginAndPassword(anyString(), anyString()))
                    .thenReturn(new User.Builder().login("login").password("password").build());
            User result = instance.findUserByLoginAndPassword("login", "password");
            Assertions.assertEquals(new User.Builder().login("login").password("password").build(), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of updateUserPassword method, of class UserDaoImpl.
     */
    @Test
    void testUpdateUserPassword() {
        try {
            when(instance.updateUserPassword(any())).thenReturn(true);
            boolean result = instance.updateUserPassword(new User());
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of deleteUser method, of class UserDaoImpl.
     */
    @Test
    void testDeleteUser() {
        try {
            when(instance.deleteUser(anyInt())).thenReturn(true);
            boolean result = instance.deleteUser(10);
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of restoreUser method, of class UserDaoImpl.
     */
    @Test
    void testRestoreUser() {
        try {
            when(instance.restoreUser(anyInt())).thenReturn(true);
            boolean result = instance.restoreUser(10);
            Assertions.assertTrue(result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }

    /**
     * Test of findAllUser method, of class UserDaoImpl.
     */
    @Test
    void testFindAllUser() {
        try {
            when(instance.findAllUser()).thenReturn(Collections.singletonList(new User()));
            List<User> result = instance.findAllUser();
            Assertions.assertEquals(Collections.singletonList(new User()), result);
        } catch (DaoException e) {
            Assertions.fail(e);
        }
    }
}