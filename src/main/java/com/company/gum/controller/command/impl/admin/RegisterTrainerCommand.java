package com.company.gum.controller.command.impl.admin;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Trainer;
import com.company.gum.model.entity.User;
import com.company.gum.model.service.TrainerService;
import com.company.gum.model.service.impl.TrainerServiceImpl;
import com.company.gum.model.validator.FormValidator;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * The Class RegisterTrainerCommand.
 *
 * @author Vladislav Kuzmich
 */
public class RegisterTrainerCommand implements Command {

    /** The trainer service. */
    private final TrainerService trainerService = TrainerServiceImpl.getInstance();
    
    /** The validator. */
    private final FormValidator validator = FormValidator.getInstance();


    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     */
    @Override
    public Router execute(SessionRequestContent requestContent) {
        Router router;
        String login = requestContent.getParameterByName(USER_LOGIN).strip();
        String password = requestContent.getParameterByName(USER_PASSWORD).strip();
        String repeatedPassword = requestContent.getParameterByName(REPEAT_PASSWORD).strip();
        String name = requestContent.getParameterByName(USER_NAME).strip();
        String surname = requestContent.getParameterByName(USER_SURNAME).strip();
        String phone = requestContent.getParameterByName(USER_PHONE).strip();
        String mail = requestContent.getParameterByName(USER_MAIL).strip();
        User.UserRole role = User.UserRole.TRAINER;

        boolean isValid = true;

        if (!validator.checkLogin(login)) {
            requestContent.putAttribute(AttributeName.ERROR_MESSAGE, "invalid.login");
            isValid = false;
        }
        if (!validator.checkNameSurname(name) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.name");
            isValid = false;
        }
        if (!validator.checkNameSurname(surname) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.surname");
            isValid = false;
        }
        if (!validator.checkPhone(phone) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.phone");
            isValid = false;
        }
        if (!validator.checkMail(mail) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.email");
            isValid = false;
        }
        if (!validator.checkPassword(password) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.password");
            isValid = false;
        }
        if (!repeatedPassword.equals(password) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "passwords.not.equal");
            isValid = false;
        }

        try {
            if (isValid) {

                Trainer trainer = new Trainer.Builder().login(login).password(password).mail(mail).name(name)
                        .surname(surname).phone(phone).role(role).build();

                trainerService.createTrainer(trainer);

                router = new Router(PagePath.CLIENT_CREATED, REDIRECT);
            } else {
                router = new Router(PagePath.REGISTER, FORWARD);
            }
        } catch (ServiceException e) {
            requestContent.putAttribute(ERROR_MESSAGE, "login.already.exist");
            router = new Router(PagePath.REGISTER, FORWARD);
        }
        return router;
    }
}
