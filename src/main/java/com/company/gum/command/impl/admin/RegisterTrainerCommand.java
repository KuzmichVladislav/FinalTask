package com.company.gum.command.impl.admin;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Trainer;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.TrainerServiceImpl;
import com.company.gum.util.Validator;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.Router.RouterType.FORWARD;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class RegisterTrainerCommand implements Command {

    private final TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
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

        if (!Validator.checkLogin(login)) {
            requestContent.putAttribute(AttributeName.ERROR_MESSAGE, "invalid.login");
            isValid = false;
        }
        if (!Validator.checkNameSurname(name) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.name");
            isValid = false;
        }
        if (!Validator.checkNameSurname(surname) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.surname");
            isValid = false;
        }
        if (!Validator.checkPhone(phone) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.phone");
            isValid = false;
        }
        if (!Validator.checkMail(mail) && isValid) {
            requestContent.putAttribute(ERROR_MESSAGE, "invalid.email");
            isValid = false;
        }
        if (!Validator.checkPassword(password) && isValid) {
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
