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

import java.util.List;

import static com.company.gum.controller.command.AttributeName.ORDERS;
import static com.company.gum.controller.command.AttributeName.USER_ID;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class ShowAllOrdersByClientCommand.
 *
 * @author Vladislav Kuzmich
 */
public class ShowAllOrdersByClientCommand implements Command {

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