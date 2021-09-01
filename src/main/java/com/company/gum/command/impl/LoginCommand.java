package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.ErrorMessageKey;
import com.company.gum.command.PagePath;
import com.company.gum.command.ParameterName;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
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

public class LoginCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    private UserService userService = UserServiceImpl.getInstance();
    private AdminService adminService = AdminServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();
    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String login = requestContent.getParameterByName(ParameterName.USER_LOGIN);
        String password = requestContent.getParameterByName(ParameterName.USER_PASSWORD);

        User user;
        String page;
        try {
            if (Validator.checkLogin(login) && Validator.checkPassword(password)) {
                user = userService.findUserByLoginAndPassword(login, password);
                if (user != null) {
                    requestContent.putSessionAttribute(ParameterName.USER_ID, user.getId());
//                    requestContent.putSessionAttribute(ParameterName.USER_PROFILE_IMG_PATH, user.getProfileImagePath());

//                    switch (user.getRole()) {
//                        case ADMIN:
//                            Admin admin = adminService.find(user.getId());
//                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, admin.getRole().name());
//                            requestContent.putSessionAttribute(AttributeName.USER_NAME, admin.getName());
//                            requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, admin.getLastName());
//                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, admin.getMail());
//                            page = PagePath.WELCOME_PATH;
//                            break;
//                        case TRAINER:
//                            Trainer trainer = trainerService.find(user.getId());
//                            requestContent.putSessionAttribute(AttributeName.USER_ROLE, trainer.getRole().name());
//                            requestContent.putSessionAttribute(AttributeName.USER_NAME, trainer.getName());
//                            requestContent.putSessionAttribute(AttributeName.USER_LAST_NAME, trainer.getLastName());
//                            requestContent.putSessionAttribute(AttributeName.USER_REGISTER_DATE, trainer.getRegisterDateTime());
//                            requestContent.putSessionAttribute(AttributeName.USER_PHONE, trainer.getPhone());
//                            requestContent.putSessionAttribute(AttributeName.USER_MAIL, trainer.getMail());
//                            page = PagePath.WELCOME_PATH;
//                            break;
//                        case CLIENT:
                    Client client = clientService.findClientById(user.getId());
                    requestContent.putSessionAttribute(ParameterName.USER_ROLE, client.getRole().name());
                    requestContent.putSessionAttribute(ParameterName.USER_NAME, client.getName());
                    requestContent.putSessionAttribute(ParameterName.USER_LAST_NAME, client.getSurname());
                    requestContent.putSessionAttribute(ParameterName.USER_REGISTER_DATE, client.getRegisterDate());
//                            requestContent.putSessionAttribute(ParameterName.USER_DISCOUNT, client.getDiscount());
//                            requestContent.putSessionAttribute(ParameterName.USER_DISCOUNT_LEVEL, client.getDiscountLevel());
                    requestContent.putSessionAttribute(ParameterName.USER_PHONE, client.getPhone());
//                            requestContent.putSessionAttribute(ParameterName.USER_CASH, client.getCash());
                    requestContent.putSessionAttribute(ParameterName.USER_MAIL, client.getMail());
                    page = PagePath.WELCOME_PATH;
//                            break;
//                        default:
//                            throw new CommandException();
//                    }
                    requestContent.putSessionAttribute(ParameterName.USER_AUTHORIZATION, true);
                } else {
                    page = (String) requestContent.getSessionAttributeByName(ParameterName.CURRENT_PAGE);
                    requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.WRONG_LOGIN_OR_PASSWORD);
                }

            } else {
                requestContent.putAttribute(ParameterName.ERROR_MESSAGE, ErrorMessageKey.INVALID_LOGIN_OR_PASSWORD);
                page = (String) requestContent.getSessionAttributeByName(ParameterName.CURRENT_PAGE);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}