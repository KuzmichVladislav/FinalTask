package com.company.gum.command.impl.client;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.ErrorMessageKey;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditClientProfile implements Command {
    private static Logger logger = LogManager.getLogger();

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValid = true;
        try {
            Integer clientId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String userName = requestContent.getParameterByName(AttributeName.USER_NAME);
            String userSurname = requestContent.getParameterByName(AttributeName.USER_SURNAME);
            String userPhone = requestContent.getParameterByName(AttributeName.USER_PHONE);
            String userMail = requestContent.getParameterByName(AttributeName.USER_MAIL);

            if (!Validator.checkNameSurname(userName)) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_NAME);
            }
            if (!Validator.checkNameSurname(userSurname) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_SURNAME_NAME);
            }
            if (!Validator.checkPhone(userPhone) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PHONE);
            }
            if (!Validator.checkMail(userMail) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);
            }

            if (isValid) {
                Client client = new Client.Builder()
                        .id(clientId)
                        .name(userName)
                        .surname(userSurname)
                        .mail(userMail)
                        .phone(userPhone)
                        .build();

                clientService.editClient(client);

                client = clientService.findClientById(clientId);

                requestContent.putSessionAttribute(AttributeName.USER_NAME, client.getName());
                requestContent.putSessionAttribute(AttributeName.USER_SURNAME, client.getSurname());
                requestContent.putSessionAttribute(AttributeName.USER_PHONE, client.getPhone());
                requestContent.putSessionAttribute(AttributeName.USER_MAIL, client.getMail());
                page = PagePath.CLIENT_PROFILE;
            } else {
                page = PagePath.CLIENT_PROFILE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
