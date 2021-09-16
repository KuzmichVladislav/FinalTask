package com.company.gum.command.order;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Order;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;
import com.company.gum.service.impl.OrderServiceImpl;

public class ClientOrderDetail implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Integer orderId = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            Order order = orderService.findOrder(orderId);
            page = PagePath.CLIENT_ORDER_DETAIL;
            System.out.println(order);
            requestContent.putAttribute(AttributeName.ORDER, order);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
