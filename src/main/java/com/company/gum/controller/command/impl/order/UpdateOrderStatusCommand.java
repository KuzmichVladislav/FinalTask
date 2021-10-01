package com.company.gum.controller.command.impl.order;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Client;
import com.company.gum.model.entity.Order;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.service.OrderService;
import com.company.gum.model.service.impl.ClientServiceImpl;
import com.company.gum.model.service.impl.OrderServiceImpl;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateOrderStatusCommand.
 */
public class UpdateOrderStatusCommand implements Command {

    /**
     * The order service.
     */
    private final OrderService orderService = OrderServiceImpl.getInstance();

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     */
    @Override
    public Router execute(SessionRequestContent requestContent) {

        Router router;
        Order order = null;
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
            Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(requestContent.getParameterByName(ORDER_STATUS));

            if (orderStatus == Order.OrderStatus.ACCEPTED) {
                int userId = (Integer) requestContent.getSessionAttributeByName(USER_ID);
                order = orderService.findOrder(orderId);
                ClientService clientService = ClientServiceImpl.getInstance();
                clientService.withdrawMoney(userId, order.getPrice());
                Client client = clientService.findClientById(userId);
                requestContent.putSessionAttribute(USER_MONEY, client.getMoney());
            }
            orderService.editOrderStatus(orderId, orderStatus);
            router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        } catch (ServiceException e) {
            requestContent.putAttribute(ERROR_MESSAGE, "not.enough.funds");
            requestContent.putAttribute(ORDER, order);
            router = new Router(PagePath.CLIENT_ORDER_DETAIL, FORWARD);
        }
        return router;
    }
}
