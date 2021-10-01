package com.company.gum.dao.impl;

import com.company.gum.entity.Admin;
import com.company.gum.exception.DaoException;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class AdminDaoImplTest {
    @Mock
    Logger logger;
    @Mock
    AdminDaoImpl instance;
    @InjectMocks
    AdminDaoImpl adminDaoImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInstance() {
        AdminDaoImpl result = AdminDaoImpl.getInstance();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testFindAdminById() throws SQLException, DaoException {
        when(instance.getAdminFromResultSet(any())).thenReturn(new Admin());

        Admin result = adminDaoImpl.findAdminById(10);
        Assertions.assertEquals(new Admin(), result);
    }

    @Test
    void testFindAllAdmin() throws SQLException, DaoException {
        when(instance.getAdminFromResultSet(any())).thenReturn(new Admin());

        List<Admin> result = adminDaoImpl.findAllAdmin();
        Assertions.assertEquals(Arrays.<Admin>asList(new Admin()), result);
    }

    @Test
    void testEditAdmin() throws DaoException, SQLException {
        when(instance.getAdminFromResultSet(any())).thenReturn(new Admin());

        boolean result = adminDaoImpl.editAdmin(new Admin.Builder().id(13).build());
        Assertions.assertEquals(true, result);
    }

    @Test
    void testDeleteUser() throws DaoException {
        boolean result = adminDaoImpl.deleteUser(0);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testRestoreUser() throws DaoException {
        boolean result = adminDaoImpl.restoreUser(0);
        Assertions.assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme