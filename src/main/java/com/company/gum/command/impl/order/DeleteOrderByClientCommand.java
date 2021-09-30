package com.company.gum.command.impl.order;

import com.company.gum.command.Command;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;
import com.company.gum.service.impl.OrderServiceImpl;

import static com.company.gum.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.command.AttributeName.ORDER_ID;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class DeleteOrderByClientCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
            orderService.deleteOrder(orderId);
            router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}