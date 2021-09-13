package com.company.gum.command.impl;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.UserService;
import com.company.gum.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class UploadImageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
        ByteArrayInputStream photo = (ByteArrayInputStream) requestContent.getAttributeByName(AttributeName.USER_PROFILE_PHOTO_PATH);

        String page;
        try {
            User user;
            user = new User();
            user.setId(userId);
            user.setPhoto(photo.readAllBytes());
            userService.updateUserImage2(user);

            String base64Image = Base64.getEncoder().encodeToString(user.getPhoto());

            requestContent.putSessionAttribute(AttributeName.USER_PHOTO, base64Image);

            page = PagePath.CLIENT_PROFILE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}