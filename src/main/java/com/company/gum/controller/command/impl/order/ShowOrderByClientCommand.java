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

// TODO: Auto-generated Javadoc
/**
 * The Class ShowOrderByClientCommand.
 */
public class ShowOrderByClientCommand implements Command {

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
            router = new Router(PagePath.CLIENT_ORDER_DETAIL, FORWARD);
            requestContent.putAttribute(ORDER, order);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
