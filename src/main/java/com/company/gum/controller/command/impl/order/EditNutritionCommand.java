package com.company.gum.controller.command.impl.order;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.OrderService;
import com.company.gum.model.service.impl.OrderServiceImpl;
import com.company.gum.model.util.UtilClass;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

public class EditNutritionCommand implements Command {

    private final OrderService orderService = OrderServiceImpl.getInstance();

    Router router;

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
            String nutrition = UtilClass.getInstance().getStringFromDescription(requestContent, NUTRITION);
            orderService.editNutrition(orderId, nutrition);
            router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
