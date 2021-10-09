package com.company.gum.controller.command.impl.client;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Client;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.service.impl.ClientServiceImpl;
import com.company.gum.model.validator.FormValidator;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * The Class RefillMoneyCommand.
 *
 * @author Vladislav Kuzmich
 */
public class RefillMoneyCommand implements Command {

    /** The client service. */
    private final ClientService clientService = ClientServiceImpl.getInstance();
    
    /** The validator. */
    private final FormValidator validator = FormValidator.getInstance();


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
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(USER_ID);
            boolean isValid = true;
            String stringMoney = requestContent.getParameterByName(MONEY);
            if (!validator.checkMoney(stringMoney)) {
                requestContent.putAttribute(ERROR_MESSAGE, "amount.is.not.valid");
                isValid = false;
            }
            if (isValid) {
                BigDecimal money = new BigDecimal(stringMoney, MathContext.DECIMAL32);
                boolean isUpdated = clientService.refillMoney(clientId, money);
                if (isUpdated) {
                    requestContent.putAttribute(MONEY, money.doubleValue());
                    Client client = clientService.findClientById(clientId);
                    requestContent.putSessionAttribute(USER_MONEY, client.getMoney());
                    router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), REDIRECT);
                } else {
                    router = new Router(PagePath.REFILL_MONEY, FORWARD);
                }
            } else {
                router = new Router(PagePath.REFILL_MONEY, FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
