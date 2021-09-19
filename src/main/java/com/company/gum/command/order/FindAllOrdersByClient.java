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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllOrdersByClient implements Command {

    private static Logger logger = LogManager.getLogger();

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            Object obj = requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            if (obj != null && obj.getClass() == Integer.class) {
                int userId = (Integer) obj;

                List<Order> orders;

                orders = orderService.findActiveOrderByClient(userId);
                page = PagePath.CLIENT_ORDERS;
                requestContent.putAttribute(AttributeName.ORDERS, orders);
            } else {
                throw new CommandException("No user in session");
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
