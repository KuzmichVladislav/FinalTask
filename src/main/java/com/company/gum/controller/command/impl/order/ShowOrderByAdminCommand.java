package com.company.gum.controller.command.impl.order;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Order;
import com.company.gum.model.service.OrderService;
import com.company.gum.model.service.impl.OrderServiceImpl;

import static com.company.gum.controller.command.AttributeName.ORDER;
import static com.company.gum.controller.command.AttributeName.ORDER_ID;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class ShowOrderByAdminCommand.
 *
 * @author Vladislav Kuzmich
 */
public class ShowOrderByAdminCommand implements Command {

    /**
     * The order service.
     */
    private final OrderService orderService = OrderServiceImpl.getInstance();

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
            int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
            Order order = orderService.findOrder(orderId);
            router = new Router(PagePath.ADMIN_ORDER_DETAIL, FORWARD);
            requestContent.putAttribute(ORDER, order);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
