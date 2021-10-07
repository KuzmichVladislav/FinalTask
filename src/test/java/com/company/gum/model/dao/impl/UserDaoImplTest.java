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

import java.sql.SQLException;
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
     * Test find user by id.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testFindUserById() throws DaoException {
        when(instance.findUserById(anyInt())).thenReturn(new User());
        User result = instance.findUserById(10);
        Assertions.assertEquals(new User(), result);
    }

    /**
     * Test find user by login and password.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testFindUserByLoginAndPassword() throws DaoException {
        when(instance.findUserByLoginAndPassword(anyString(), anyString()))
                .thenReturn(new User.Builder().login("login").password("password").build());
        User result = instance.findUserByLoginAndPassword("login", "password");
        Assertions.assertEquals(new User.Builder().login("login").password("password").build(), result);
    }

    /**
     * Test update user password.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testUpdateUserPassword() throws DaoException {
        when(instance.updateUserPassword(any())).thenReturn(true);
        boolean result = instance.updateUserPassword(new User());
        Assertions.assertTrue(result);
    }

    /**
     * Test delete user.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testDeleteUser() throws DaoException {
        when(instance.deleteUser(anyInt())).thenReturn(true);
        boolean result = instance.deleteUser(10);
        Assertions.assertTrue(result);
    }

    /**
     * Test restore user.
     *
     * @throws DaoException the dao exception
     */
    @Test
    void testRestoreUser() throws DaoException {
        when(instance.restoreUser(anyInt())).thenReturn(true);
        boolean result = instance.restoreUser(10);
        Assertions.assertTrue(result);
    }

    /**
     * Test find all user.
     *
     * @throws SQLException the SQL exception
     * @throws DaoException the dao exception
     */
    @Test
    void testFindAllUser() throws DaoException {
        when(instance.findAllUser()).thenReturn(Collections.singletonList(new User()));
        List<User> result = instance.findAllUser();
        Assertions.assertEquals(Collections.singletonList(new User()), result);
    }
}
