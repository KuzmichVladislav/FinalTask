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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;



@WebServlet(urlPatterns = {"/uploadServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class _UploadServlet extends HttpServlet {

    private static final String IMAGE_NAME_DIR = "image";
    private static final String USER_NAME_DIR = "user";
    private static final String UPLOAD_DIR = IMAGE_NAME_DIR + File.separator + USER_NAME_DIR;
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applicationDir = req.getServletContext().getRealPath("");
        int userId = (Integer) req.getSession().getAttribute(AttributeName.USER_ID);
        String uploadFileDir = applicationDir + req + File.separator + userId + File.separator;
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String imagePath = null;
        InputStream inputStream = null;

        for (Part part : req.getParts()) {
            if (part.getSubmittedFileName() != null && part.getContentType().contains("image")) {
                inputStream = part.getInputStream();
                part.write(uploadFileDir + part.getSubmittedFileName());
                imagePath = req.getServletContext().getContextPath() + File.separator + UPLOAD_DIR + File.separator + userId + File.separator + part.getSubmittedFileName();
            }
        }
        req.setAttribute(AttributeName.USER_PROFILE_IMAGE, inputStream);
        req.getRequestDispatcher(PagePath.MAIN_CONTROLLER).forward(req, resp);
    }
}

