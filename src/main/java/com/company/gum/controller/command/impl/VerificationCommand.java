package com.company.gum.controller.command.impl;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.service.impl.ClientServiceImpl;

import static com.company.gum.controller.command.AttributeName.USER_ID;
import static com.company.gum.controller.command.AttributeName.VERIFICATION;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

// TODO: Auto-generated Javadoc
/**
 * The Class VerificationCommand.
 */
public class VerificationCommand implements Command {

    /**
     * The client service.
     */
    private final ClientService clientService = ClientServiceImpl.getInstance();

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;

        int clientId = Integer.parseInt(requestContent.getParameterByName(USER_ID));

        try {
            boolean isVerified = clientService.verification(clientId);
            requestContent.putAttribute(VERIFICATION, isVerified);
            router = new Router(PagePath.VERIFICATION, FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
