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

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    private static final String IMAGE_NAME_DIR = "image";
    private static final String USER_NAME_DIR = "user";
    private static final String UPLOAD_DIR = IMAGE_NAME_DIR + "/" + USER_NAME_DIR;
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationDir = request.getServletContext().getRealPath("");
        int userId = (Integer) request.getSession().getAttribute(AttributeName.USER_ID);
        String uploadFileDir = "C:/"+ UPLOAD_DIR + "/" + userId + "/";

       // String uploadFileDir =".src/main/resources/image/";

        System.out.println(uploadFileDir);
        //file:///C:/Program%20Files/Apache%20Software%20Foundation/Tomcat%209.0_Tomcat9.0.50/webapps/image/user/13/1.png
        File fileSaveDir = new File(uploadFileDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String imagePath = null;

        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null && part.getContentType().contains("image")) {
                part.write(uploadFileDir + part.getSubmittedFileName());

                imagePath = "file:///C:/"+ UPLOAD_DIR + "/" + userId + "/" + part.getSubmittedFileName();
                System.out.println(imagePath);
            }
        }
        request.setAttribute(AttributeName.USER_PROFILE_PHOTO_PATH, imagePath);
        request.getRequestDispatcher(PagePath.MAIN_CONTROLLER).forward(request, response);
    }
}
