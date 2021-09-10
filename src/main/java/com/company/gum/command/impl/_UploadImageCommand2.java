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

public class _UploadImageCommand2 implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
        ByteArrayInputStream photo = (ByteArrayInputStream) requestContent.getAttributeByName(AttributeName.USER_PROFILE_PHOTO_PATH);
        System.out.println(photo);

        String page;
        try {
            User user;
            // if (photo != null) {
            user = new User();
            user.setId(userId);
            user.setPhoto(photo.readAllBytes());
            userService.updateUserImage2(user);

            String base64Image = Base64.getEncoder().encodeToString(user.getPhoto());
            System.out.println(base64Image);
            // BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
            // ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //ImageIO.write(bImage, "jpg", bos );
            //   byte [] data = bos.toByteArray();
            //ByteArrayInputStream bis = new ByteArrayInputStream(data);


          //  BufferedImage bImage2 = ImageIO.read(photo);
            // ImageIO.write(bImage2, "png", new File("output.png") );
            // System.out.println("image created");



            // user = userService.findUserById(userId);
            requestContent.putSessionAttribute(AttributeName.USER_PHOTO, base64Image);
            //  System.out.println(img);

            //    }

            page = PagePath.CLIENT_PROFILE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}