package com.company.gum.command.impl.admin;

import com.company.gum.command.Command;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.util.Validator;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.ErrorMessageKey.INVALID_DISCOUNT;
import static com.company.gum.command.Router.RouterType.FORWARD;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class AssignDiscountCommand implements Command {

	ClientService clientService = ClientServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		Router router;
		try {
			boolean isValid = true;
			int clientId = Integer.parseInt(requestContent.getParameterByName(CLIENT_ID));
			String stringDiscount = requestContent.getParameterByName(DISCOUNT);
			if (!Validator.checkDiscount(stringDiscount)) {
				requestContent.putAttribute(ERR_MESSAGE, INVALID_DISCOUNT);
				isValid = false;
			}
			if (isValid) {
				BigDecimal discount = new BigDecimal(stringDiscount, MathContext.DECIMAL32);
				boolean isUpdated = clientService.assignDiscount(clientId, discount);
				if (isUpdated) {
					router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), REDIRECT);
				} else {
					requestContent.putAttribute(ERR_MESSAGE, INVALID_DISCOUNT);
					router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
				}
			} else {
				requestContent.putAttribute(ERR_MESSAGE, INVALID_DISCOUNT);
				router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
