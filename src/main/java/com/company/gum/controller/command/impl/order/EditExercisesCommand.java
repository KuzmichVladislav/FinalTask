package com.company.gum.controller.command.impl.order;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.OrderService;
import com.company.gum.model.service.impl.OrderServiceImpl;
import com.company.gum.model.util.UtilClass;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class EditExercisesCommand.
 *
 * @author Vladislav Kuzmich
 */
public class EditExercisesCommand implements Command {

	/**
	 * The order service.
	 */
	private final OrderService orderService = OrderServiceImpl.getInstance();

	/**
	 * The router.
	 */
	Router router;

	/**
	 * Execute.
	 *
	 * @param requestContent the request content
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		try {
			int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
			String exercises = UtilClass.getInstance().getStringFromDescription(requestContent, EXERCISES);
			orderService.editExercises(orderId, exercises);
			router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
