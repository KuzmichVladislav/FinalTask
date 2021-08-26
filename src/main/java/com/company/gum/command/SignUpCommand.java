package com.company.gum.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpCommand implements Command {
    private static Logger logger = LogManager.getLogger(SignUpCommand.class);

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException, ServiceException {
        String page;

        String login = requestContent.getParameterByName(AttributeName.USER_LOGIN).strip();
        String password = requestContent.getParameterByName(AttributeName.USER_PASSWORD).strip();
        String repeatedPassword = requestContent.getParameterByName(AttributeName.REPEAT_PASSWORD).strip();
        String name = requestContent.getParameterByName(AttributeName.USER_NAME).strip();
        String lastName = requestContent.getParameterByName(AttributeName.USER_LAST_NAME).strip();
        String phone = requestContent.getParameterByName(AttributeName.USER_PHONE).strip();
        String mail = requestContent.getParameterByName(AttributeName.USER_MAIL).strip();

        Client client = new Client();
        client.setLogin(login);
        client.setPassword(password);
        client.setName(name);
        client.setSurname(lastName);
        client.setPhone(phone);
        client.setMail(mail);

        clientService.createClient(client);
        page = PagePath.CLIENT_CREATED;
        return page;
    }
}
