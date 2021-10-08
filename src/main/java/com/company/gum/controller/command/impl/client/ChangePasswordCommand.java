package com.company.gum.controller.command.impl.client;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.User;
import com.company.gum.model.service.UserService;
import com.company.gum.model.service.impl.UserServiceImpl;
import com.company.gum.model.util.JBCryptPasswordEncoder;
import com.company.gum.model.validator.FormValidator;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

public class ChangePasswordCommand implements Command {

    private final FormValidator validator = FormValidator.getInstance();
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

            if (!validator.checkPassword(currentPassword)) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "current.password.invalid");
            }
            if (!validator.checkPassword(newPassword) && isValid) {
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
                    router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
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
