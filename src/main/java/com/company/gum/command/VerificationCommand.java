package com.company.gum.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;

public class VerificationCommand implements Command {

    private ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        int clientId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));

        try {
            boolean isVerified = clientService.verification(clientId);
            requestContent.putAttribute(AttributeName.VERIFICATION, isVerified);
            page = PagePath.VERIFICATION_PATH;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
