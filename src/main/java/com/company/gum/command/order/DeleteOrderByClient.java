package com.company.gum.command.order;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;
import com.company.gum.service.impl.OrderServiceImpl;

public class DeleteOrderByClient implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(AttributeName.ORDER_ID));
            orderService.deleteOrder(orderId);
            requestContent.putAttribute(AttributeName.ORDER_ID, orderId);
            page = PagePath.ORDER_DELETED_BY_CLIENT;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
