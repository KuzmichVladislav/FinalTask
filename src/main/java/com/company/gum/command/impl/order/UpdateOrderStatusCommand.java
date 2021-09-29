package com.company.gum.command.impl.order;

import com.company.gum.command.*;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Client;
import com.company.gum.entity.Order;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.OrderService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.service.impl.OrderServiceImpl;

import static com.company.gum.command.AttributeName.ORDER_ID;
import static com.company.gum.command.AttributeName.ORDER_STATUS;
import static com.company.gum.command.Router.RouterType.FORWARD;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class UpdateOrderStatusCommand implements Command {
	OrderService orderService = OrderServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {

		Router router;
		Order order = null;
		try {
			int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
			Order.OrderStatus orderStatus = Order.OrderStatus.valueOf(requestContent.getParameterByName(ORDER_STATUS));

			if (orderStatus == Order.OrderStatus.ACCEPTED) {
				int userId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
				order = orderService.findOrder(orderId);
				ClientService clientService = ClientServiceImpl.getInstance();
				clientService.withdrawMoney(userId, order.getPrice());
				Client client = clientService.findClientById(userId);
				requestContent.putSessionAttribute(AttributeName.USER_MONEY, client.getMoney());
			}
			orderService.editOrderStatus(orderId, orderStatus);
			router = new Router(PagePath.ORDER_UPDATED, REDIRECT);
		} catch (ServiceException e) {
			requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.ACCOUNT_NOT_ENOUGH_AMOUNT);
			requestContent.putAttribute(AttributeName.ORDER, order);
			router = new Router(PagePath.CLIENT_ORDER_DETAIL, FORWARD);
		}
		return router;
	}
}
