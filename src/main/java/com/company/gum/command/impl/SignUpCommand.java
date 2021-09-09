package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.ErrorMessageKey;
import com.company.gum.command.PagePath;
import com.company.gum.command.AttributeName;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.util.Validator;

import static com.company.gum.command.AttributeName.*;

public class SignUpCommand implements Command {

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        String login = requestContent.getParameterByName(USER_LOGIN).strip();
        String password = requestContent.getParameterByName(USER_PASSWORD).strip();
        String repeatedPassword = requestContent.getParameterByName(REPEAT_PASSWORD).strip();
        String name = requestContent.getParameterByName(USER_NAME).strip();
        String surname = requestContent.getParameterByName(USER_SURNAME).strip();
        String phone = requestContent.getParameterByName(USER_PHONE).strip();
        String mail = requestContent.getParameterByName(USER_MAIL).strip();

        boolean isValid = true;

        if (!Validator.checkLogin(login)) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_LOGIN);
            isValid = false;
        }
        if (!Validator.checkNameSurname(name) && isValid) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_NAME);
            isValid = false;
        }
        if (!Validator.checkNameSurname(surname) && isValid) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_SURNAME_NAME);
            isValid = false;
        }
        if (!Validator.checkPhone(phone) && isValid) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PHONE);
            isValid = false;
        }
        if (!Validator.checkMail(mail) && isValid) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);
            isValid = false;
        }
        if (!Validator.checkPassword(password) && isValid) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PASSWORD);
            isValid = false;
        }
        if (!repeatedPassword.equals(password) && isValid) {
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.PASSWORDS_NOT_EQUAL);
            isValid = false;
        }

        try {
            if (isValid) {

                Client client = new Client.Builder().login(login)
                        .password(password)
                        .mail(mail)
                        .name(name)
                        .surname(surname)
                        .phone(phone)
                        .build();

                clientService.createClient(client);

                page = PagePath.CLIENT_CREATED;
            } else {
                page = PagePath.SIGN_UP;
            }
        } catch (ServiceException e) {
            page = PagePath.SIGN_UP;
            requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.LOGIN_ALREADY_EXIST);
        }
        return page;
    }
}
