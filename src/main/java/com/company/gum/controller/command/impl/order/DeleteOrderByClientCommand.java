package com.company.gum.controller.command.impl.order;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.OrderService;
import com.company.gum.model.service.impl.OrderServiceImpl;

import static com.company.gum.controller.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.controller.command.AttributeName.ORDER_ID;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class DeleteOrderByClientCommand.
 *
 * @author Vladislav Kuzmich
 */
public class DeleteOrderByClientCommand implements Command {

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
			orderService.deleteOrder(orderId);
			router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
