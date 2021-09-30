package com.company.gum.command.impl.order;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Order;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;
import com.company.gum.service.impl.OrderServiceImpl;

import static com.company.gum.command.AttributeName.ORDER;
import static com.company.gum.command.AttributeName.ORDER_ID;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class showOrderByClientCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
            Order order = orderService.findOrder(orderId);
            router = new Router(PagePath.CLIENT_ORDER_DETAIL, FORWARD);
            requestContent.putAttribute(ORDER, order);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
