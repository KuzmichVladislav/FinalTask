/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.company.gum.controller;

import com.company.gum.entity.User;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

/**
 * @author kyzme
 */
public class MainControllerTest {
    @Mock
    Logger logger;
    @Mock
    ResourceBundle lStrings;
    @Mock
    ServletConfig config;
    @InjectMocks
    MainController mainController;

    public MainControllerTest() {
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of doGet method, of class MainController.
     */
    @Test
    public void testDoGet() throws Exception {
        User user = new User.Builder().login("admin")
                .password("admin22").build();

        System.out.println("doGet");
        RequestDispatcher rd = mock(RequestDispatcher.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getRequestDispatcher("/web/index.jsp")).thenReturn(rd);
        when(req.getParameter("userLogin")).thenReturn(user.getLogin());
        when(req.getParameter("userPassword")).thenReturn(user.getPassword());

        req.setAttribute("userLogin", "admin");
        System.out.println(req.getAttribute("userLogin"));
        System.out.println(req.getAttributeNames());

        MainController instance = new MainController();
        instance.doPost(req, resp);
        verify(rd).forward(req, resp);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of doPost method, of class MainController.
     */
    @Test
    public void testDoPost() throws Exception {
        System.out.println("doPost");
        HttpServletRequest req = null;
        HttpServletResponse resp = null;
        MainController instance = new MainController();
        instance.doPost(req, resp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
