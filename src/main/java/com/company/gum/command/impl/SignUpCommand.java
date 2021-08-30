package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;

import static com.company.gum.command.ParameterName.*;

public class SignUpCommand implements Command {

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        String login = requestContent.getParameterByName(USER_LOGIN).strip();
        String password = requestContent.getParameterByName(USER_PASSWORD).strip();
        String name = requestContent.getParameterByName(USER_NAME).strip();
        String surname = requestContent.getParameterByName(USER_LAST_NAME).strip();
        String phone = requestContent.getParameterByName(USER_PHONE).strip();
        String mail = requestContent.getParameterByName(USER_MAIL).strip();

        Client client = new Client.Builder().login(login)
                .password(password)
                .mail(mail)
                .name(name)
                .surname(surname)
                .phone(phone)
                .build();

        try {
            clientService.createClient(client);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        page = PagePath.CLIENT_CREATED;
        return page;
    }
}
