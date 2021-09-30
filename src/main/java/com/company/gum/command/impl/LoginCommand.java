package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.entity.Trainer;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.TrainerService;
import com.company.gum.service.UserService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.service.impl.TrainerServiceImpl;
import com.company.gum.service.impl.UserServiceImpl;
import com.company.gum.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.Router.RouterType.FORWARD;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private UserService userService = UserServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(USER_LOGIN);
        String password = requestContent.getParameterByName(USER_PASSWORD);
        User user;
        Router router;
        try {
            if (Validator.checkLogin(login) && Validator.checkPassword(password)) {
                user = userService.findUserByLoginAndPassword(login, password);
                if (user != null) {
                    if (user.isVerification()) {
                        requestContent.putSessionAttribute(USER_ID, user.getId());
                        requestContent.putSessionAttribute(USER_LOGIN, user.getLogin());
                        requestContent.putSessionAttribute(USER_ROLE, user.getRole().name());
                        requestContent.putSessionAttribute(USER_NAME, user.getName());
                        requestContent.putSessionAttribute(USER_SURNAME, user.getSurname());
                        requestContent.putSessionAttribute(USER_MAIL, user.getMail());
                        requestContent.putSessionAttribute(USER_PHOTO, user.getBase64Image());
                        switch (user.getRole()) {
                            case ADMIN:
                                router = new Router(PagePath.WELCOME, REDIRECT);
                                break;
                            case TRAINER:
                                Trainer trainer = trainerService.findTrainerById(user.getId());
                                requestContent.putSessionAttribute(USER_REGISTER_DATE, trainer.getRegisterDate());
                                requestContent.putSessionAttribute(USER_PHONE, trainer.getPhone());
                                requestContent.putSessionAttribute(DESCRIPTION, trainer.getDescription());
                                requestContent.putSessionAttribute(EXPERIENCE, trainer.getExperience());
                                router = new Router(PagePath.WELCOME, REDIRECT);
                                break;
                            case CLIENT:
                                Client client = clientService.findClientById(user.getId());
                                requestContent.putSessionAttribute(USER_REGISTER_DATE, client.getRegisterDate());
                                requestContent.putSessionAttribute(USER_DISCOUNT, client.getDiscount());
                                requestContent.putSessionAttribute(USER_DISCOUNT_LEVEL, client.getDiscountLevel());
                                requestContent.putSessionAttribute(USER_PHONE, client.getPhone());
                                requestContent.putSessionAttribute(USER_MONEY, client.getMoney());
                                router = new Router(PagePath.WELCOME, REDIRECT);
                                break;
                            default:
                                logger.debug("Debug exception for testing. When adding a user with a new role.");
                                throw new CommandException("Debug exception for testing. When adding a user with a new role.");
                        }
                        requestContent.putSessionAttribute(USER_AUTHORIZATION, true);
                    } else {
                        requestContent.putAttribute(ERR_MESSAGE, "verification.error");// TODO: 9/23/2021
                        router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
                    }
                } else {
                    requestContent.putAttribute(ERR_MESSAGE, "wrong.login.or.password");
                    router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
                }
            } else {
                requestContent.putAttribute(ERR_MESSAGE, "login.or.password.is.not.valid");
                router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
