package com.company.gum.command.impl;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.ErrorMessageKey;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Admin;
import com.company.gum.entity.Client;
import com.company.gum.entity.Trainer;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.AdminService;
import com.company.gum.service.ClientService;
import com.company.gum.service.TrainerService;
import com.company.gum.service.UserService;
import com.company.gum.service.impl.AdminServiceImpl;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.service.impl.TrainerServiceImpl;
import com.company.gum.service.impl.UserServiceImpl;
import com.company.gum.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;


public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private UserService userService = UserServiceImpl.getInstance();
    private AdminService adminService = AdminServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(AttributeName.USER_LOGIN);
        String password = requestContent.getParameterByName(AttributeName.USER_PASSWORD);

        User user;
        String page;
        try {
            if (Validator.checkLogin(login) && Validator.checkPassword(password)) {
                user = userService.findUserByLoginAndPassword(login, password);
                if (user != null) {
                    requestContent.putSessionAttribute(AttributeName.USER_ID, user.getId());
                    requestContent.putSessionAttribute(AttributeName.USER_PROFILE_PHOTO_PATH, user.getProfileImage());

                    switch (user.getRole()) {
                        case ADMIN:
                            Admin admin = adminService.findAdminById(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, admin.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, admin.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_SURNAME, admin.getSurname());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, admin.getMail());
                            page = PagePath.WELCOME;
                            break;
                        case TRAINER:
                            Trainer trainer = trainerService.findTrainerById(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, trainer.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, trainer.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_SURNAME, trainer.getSurname());
                            requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, trainer.getRegisterDate());
                            requestContent.putSessionAttribute(AttributeName.USER_PHONE, trainer.getPhone());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, trainer.getMail());
                            page = PagePath.WELCOME;
                            break;
                        case CLIENT:
                            Client client = clientService.findClientById(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, client.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, client.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_SURNAME, client.getSurname());
                            requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, client.getRegisterDate());
                            requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT, client.getDiscount());
                            requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT_LEVEL, client.getDiscountLevel());
                            requestContent.putSessionAttribute(AttributeName.USER_PHONE, client.getPhone());
                            requestContent.putSessionAttribute(AttributeName.USER_MONEY, client.getMoney());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, client.getMail());
                           // requestContent.putSessionAttribute(AttributeName.USER_PHOTO, client.getPhoto());
                            String base64Image = Base64.getEncoder().encodeToString(client.getPhoto());
                            requestContent.putSessionAttribute(AttributeName.USER_PHOTO, base64Image);
                            page = PagePath.WELCOME;
                            break;
                        default:
                            throw new CommandException();
                    }
                    requestContent.putSessionAttribute(AttributeName.USER_AUTHORIZATION, true);
                } else {
                    page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.WRONG_LOGIN_OR_PASSWORD);
                }

            } else {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_LOGIN_OR_PASSWORD);
                page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}

// TODO: 9/10/2021  
/*
    private UserService userService = UserServiceImpl.getInstance();
    private AdminService adminService = AdminServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(AttributeName.USER_LOGIN);
        String password = requestContent.getParameterByName(AttributeName.USER_PASSWORD);

        User user;
        String page;
        try {
            if (Validator.checkLogin(login) && Validator.checkPassword(password)) {
                user = userService.findUserByLoginAndPassword(login, password);
                if (user != null) {
                    requestContent.putSessionAttribute(AttributeName.USER_ID, user.getId());
                    requestContent.putSessionAttribute(AttributeName.USER_PROFILE_IMAGE, user.getProfileImage());

                    switch (user.getRole()) {
                        case ADMIN:
                            Admin admin = adminService.findAdminById(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, admin.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, admin.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_SURNAME, admin.getSurname());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, admin.getMail());
                            page = PagePath.WELCOME;
                            break;
                        case TRAINER:
                            Trainer trainer = trainerService.findTrainerById(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, trainer.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, trainer.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_SURNAME, trainer.getSurname());
                            requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, trainer.getRegisterDate());
                            requestContent.putSessionAttribute(AttributeName.USER_PHONE, trainer.getPhone());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, trainer.getMail());
                            page = PagePath.WELCOME;
                            break;
                        case CLIENT:
                            Client client = clientService.findClientById(user.getId());
                            requestContent.putSessionAttribute(AttributeName.USER_LOGIN, client.getLogin());
                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, client.getRole().name());
                            requestContent.putSessionAttribute(AttributeName.USER_NAME, client.getName());
                            requestContent.putSessionAttribute(AttributeName.USER_SURNAME, client.getSurname());
                            requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, client.getRegisterDate());
                            requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT, client.getDiscount());
                            requestContent.putSessionAttribute(AttributeName.USER_DISCOUNT_LEVEL, client.getDiscountLevel());
                            requestContent.putSessionAttribute(AttributeName.USER_PHONE, client.getPhone());
                            requestContent.putSessionAttribute(AttributeName.USER_MONEY, client.getMoney());
                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, client.getMail());
                            page = PagePath.WELCOME;
                            break;
                        default:
                            throw new CommandException();
                    }
                    requestContent.putSessionAttribute(AttributeName.USER_AUTHORIZATION, true);
                } else {
                    page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
                    requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.WRONG_LOGIN_OR_PASSWORD);
                }

            } else {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_LOGIN_OR_PASSWORD);
                page = (String) requestContent.getSessionAttributeByName(AttributeName.CURRENT_PAGE);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
*/
