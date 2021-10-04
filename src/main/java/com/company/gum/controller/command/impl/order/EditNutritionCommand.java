package com.company.gum.controller.command.impl.order;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.OrderService;
import com.company.gum.model.service.impl.OrderServiceImpl;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class EditNutritionCommand.
 *
 * @author Vladislav Kuzmich
 */
public class EditNutritionCommand implements Command {

    /**
     * The order service.
     */
    private final OrderService orderService = OrderServiceImpl.getInstance();

    /**
     * The router.
     */
    Router router;

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     * @throws CommandException the command exception
     */
    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
            String nutrition = requestContent.getParameterByName(NUTRITION).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(NUTRITION)
                    : requestContent.getParameterByName(NUTRITION).strip()
                            .replace("<", "").replace(">", "");

            orderService.editNutrition(orderId, nutrition);
            router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
