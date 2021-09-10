package com.company.gum.controller;

import com.company.gum.command.AttributeName;
import com.company.gum.service.UserService;
import com.company.gum.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/image"})
public class DisplayImageServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    public DisplayImageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // int userId = (Integer) req.getSession().getAttribute(AttributeName.USER_ID);
        byte[] image = (byte[]) req.getSession().getAttribute(AttributeName.USER_PHOTO);

        resp.getOutputStream().write(image);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
