package com.company.gum.command.impl.client;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.util.Validator;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.ErrorMessageKey.INVALID_MONEY;
import static com.company.gum.command.Router.RouterType.FORWARD;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class RefillMoneyCommand implements Command {

    ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(USER_ID);
            boolean isValid = true;
            String stringMoney = requestContent.getParameterByName(MONEY);
            if (!Validator.checkMoney(stringMoney)) {
                requestContent.putAttribute(ERR_MESSAGE, INVALID_MONEY);
                isValid = false;
            }
            if (isValid) {
                BigDecimal money = new BigDecimal(stringMoney, MathContext.DECIMAL32);
                boolean isUpdated = clientService.refillMoney(clientId, money);
                if (isUpdated) {
                    requestContent.putAttribute(MONEY, money.doubleValue());
                    Client client = clientService.findClientById(clientId);
                    requestContent.putSessionAttribute(USER_MONEY, client.getMoney());
                    router = new Router(PagePath.MONEY_REFILLED, REDIRECT);
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
