package com.company.gum.command.impl.client;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.ErrorMessageKey;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.UserService;
import com.company.gum.service.impl.UserServiceImpl;
import com.company.gum.util.JBCryptPasswordEncoder;
import com.company.gum.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangePassword implements Command {

    private static final Logger logger = LogManager.getLogger();

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        boolean isValid = true;

        try {
            Integer clientId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID).strip());
            String currentPassword = requestContent.getParameterByName(AttributeName.CURRENT_PASSWORD).strip();
            String newPassword = requestContent.getParameterByName(AttributeName.NEW_PASSWORD).strip();
            String repeatedPassword = requestContent.getParameterByName(AttributeName.REPEAT_PASSWORD).strip();

            if (!Validator.checkPassword(currentPassword)) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_CURRENT_PASSWORD);
            }
            if (!Validator.checkPassword(newPassword) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_NEW_PASSWORD);
            }
            if (!repeatedPassword.equals(newPassword) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.PASSWORDS_NOT_EQUAL);
            }
            if (isValid) {
                User user = userService.findUserById(clientId);
                currentPassword = JBCryptPasswordEncoder.encode(currentPassword);
                if (currentPassword.equals(user.getPassword())) {
                    user.setPassword(newPassword);
                    userService.updateUserPassword(user);
                    page = PagePath.CLIENT_PROFILE;// TODO: 9/9/2021
                } else {
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PASSWORD);
                    page = PagePath.CLIENT_PROFILE;
                }
            } else {
                page = PagePath.CLIENT_PROFILE;
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
