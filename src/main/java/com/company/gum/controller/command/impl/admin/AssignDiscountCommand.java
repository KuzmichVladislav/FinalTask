package com.company.gum.controller.command.impl.admin;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.service.impl.ClientServiceImpl;
import com.company.gum.model.validator.FormValidator;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * The Class AssignDiscountCommand.
 *
 * @author Vladislav Kuzmich
 */
public class AssignDiscountCommand implements Command {

    /** The client service. */
    private final ClientService clientService = ClientServiceImpl.getInstance();
    
    /** The validator. */
    private final FormValidator formValidator = FormValidator.getInstance();

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
            boolean isValid = true;
            int clientId = Integer.parseInt(requestContent.getParameterByName(CLIENT_ID));
            String stringDiscount = requestContent.getParameterByName(DISCOUNT);
            if (!formValidator.checkDiscount(stringDiscount)) {
                requestContent.putAttribute(ERROR_MESSAGE, "discount.invalid");
                isValid = false;
            }
            if (isValid) {
                BigDecimal discount = new BigDecimal(stringDiscount, MathContext.DECIMAL32);
                boolean isUpdated = clientService.assignDiscount(clientId, discount);
                if (isUpdated) {
                    router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), REDIRECT);
                } else {
                    requestContent.putAttribute(ERROR_MESSAGE, "discount.invalid");
                    router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
                }
            } else {
                requestContent.putAttribute(ERROR_MESSAGE, "discount.invalid");
                router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
