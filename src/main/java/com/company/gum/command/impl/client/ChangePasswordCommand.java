package com.company.gum.command.impl.client;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.UserService;
import com.company.gum.service.impl.UserServiceImpl;
import com.company.gum.util.JBCryptPasswordEncoder;
import com.company.gum.util.Validator;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.ErrorMessageKey.*;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class ChangePasswordCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;

        boolean isValid = true;

        try {
            int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            String currentPassword = requestContent.getParameterByName(CURRENT_PASSWORD).strip();
            System.out.println(currentPassword);
            String newPassword = requestContent.getParameterByName(NEW_PASSWORD).strip();
            System.out.println(newPassword);
            String repeatedPassword = requestContent.getParameterByName(REPEAT_PASSWORD).strip();
            System.out.println(repeatedPassword);

            if (!Validator.checkPassword(currentPassword)) {
                isValid = false;
                requestContent.putAttribute(ERR_MESSAGE, INVALID_CURRENT_PASSWORD);
            }
            if (!Validator.checkPassword(newPassword) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERR_MESSAGE, INVALID_NEW_PASSWORD);
            }
            if (!repeatedPassword.equals(newPassword) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERR_MESSAGE, PASSWORDS_NOT_EQUAL);
            }
            if (isValid) {
                User user = userService.findUserById(userId);
                currentPassword = JBCryptPasswordEncoder.encode(currentPassword);
                if (currentPassword.equals(user.getPassword())) {
                    user.setPassword(newPassword);
                    userService.updateUserPassword(user);
                } else {
                    requestContent.putAttribute(ERR_MESSAGE, INVALID_PASSWORD);
                }
                router = new Router(PagePath.PASSWORDS_CHANGED, FORWARD);// TODO: 9/9/2021
            } else {
                router = new Router(PagePath.PASSWORDS_CHANGED, FORWARD);// TODO: 9/9/2021
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
