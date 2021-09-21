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

import java.util.List;

import static com.company.gum.command.AttributeName.ORDERS;
import static com.company.gum.command.AttributeName.USER_ID;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class ShowAllOrdersByClientCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(USER_ID);
            List<Order> orders;
            orders = orderService.findActiveOrderByClient(clientId);
            router = new Router(PagePath.CLIENT_ORDERS, FORWARD);
            requestContent.putAttribute(ORDERS, orders);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
