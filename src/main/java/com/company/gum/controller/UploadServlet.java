package com.company.gum.controller;

import com.company.gum.command.AttributeName;
import com.company.gum.command.PagePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


@WebServlet(urlPatterns = {"/uploadServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = null;
        for (Part part : req.getParts()) {
            if (part.getSubmittedFileName() != null && part.getContentType().contains("image")) {
                inputStream = part.getInputStream();
            }
        }
        req.setAttribute(AttributeName.USER_PROFILE_PHOTO_PATH, inputStream);
        req.getRequestDispatcher(PagePath.MAIN_CONTROLLER).forward(req, resp);
    }
}

