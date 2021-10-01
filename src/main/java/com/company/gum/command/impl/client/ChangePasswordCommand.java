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
import static com.company.gum.command.Router.RouterType.FORWARD;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class ChangePasswordCommand implements Command {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        boolean isValid = true;
        try {
            int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            String currentPassword = requestContent.getParameterByName(CURRENT_PASSWORD).strip();
            String newPassword = requestContent.getParameterByName(NEW_PASSWORD).strip();
            String repeatedPassword = requestContent.getParameterByName(REPEAT_PASSWORD).strip();

            if (!Validator.checkPassword(currentPassword)) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "current.password.invalid");
            }
            if (!Validator.checkPassword(newPassword) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "new.password.invalid");
            }
            if (!repeatedPassword.equals(newPassword) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "passwords.not.equal");
            }
            if (isValid) {
                User user = userService.findUserById(userId);
                currentPassword = JBCryptPasswordEncoder.encode(currentPassword);
                if (currentPassword.equals(user.getPassword())) {
                    user.setPassword(newPassword);
                    userService.updateUserPassword(user);
                    router = new Router(PagePath.PASSWORDS_CHANGED, REDIRECT);
                } else {
                    requestContent.putAttribute(ERROR_MESSAGE, "invalid.password");
                    router = new Router(PagePath.CHANGE_PASSWORD, FORWARD);
                }
            } else {
                router = new Router(PagePath.CHANGE_PASSWORD, FORWARD);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
