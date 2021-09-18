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

import java.math.BigDecimal;
import java.math.MathContext;

public class RefillMoney implements Command {

    ClientService clientService = ClientServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            boolean isValid = true;
            String StringMoney = requestContent.getParameterByName(AttributeName.MONEY);
            if (!Validator.checkMoney(StringMoney)) {
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_MONEY);
                isValid = false;
            }
            if (isValid) {
                BigDecimal money = new BigDecimal(StringMoney, MathContext.DECIMAL32);
                boolean isUpdated = clientService.refillMoney(clientId, money);
                if (isUpdated) {
                    requestContent.putAttribute(AttributeName.MONEY, money.doubleValue());
                    Client client = clientService.findClientById(clientId);
                    requestContent.putSessionAttribute(AttributeName.USER_MONEY, client.getMoney());
                    page = PagePath.MONEY_REFILLED;
                } else {
                    page = PagePath.REFILL_MONEY;
                }
            } else {
                page = PagePath.REFILL_MONEY;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
