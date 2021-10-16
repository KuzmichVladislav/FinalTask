package com.company.gum.controller.command.impl.order;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Duration;
import com.company.gum.model.entity.Order;
import com.company.gum.model.service.OrderService;
import com.company.gum.model.service.impl.OrderServiceImpl;
import com.company.gum.model.util.XssDefender;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * The Class CreateOrderCommand.
 *
 * @author Vladislav Kuzmich
 */
public class CreateOrderCommand implements Command {

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
			int discount = (Integer) requestContent.getSessionAttributeByName(USER_DISCOUNT);
			int trainerId = Integer.parseInt(requestContent.getParameterByName(TRAINER_ID));
			String clientComment = XssDefender.getInstance().getStringFromDescription(requestContent, COMMENT);
			String date = requestContent.getParameterByName(START_DATE);
			LocalDate startDate = date.isEmpty() ? LocalDate.now() : LocalDate.parse(date);
			String duration = requestContent.getParameterByName(DURATION);
			Duration training = Duration.values()[Integer.parseInt(duration)];
			LocalDate endDate = startDate.plus(training.day(), ChronoUnit.DAYS);
			BigDecimal price = training.getPrice().multiply(BigDecimal.valueOf(1d - 1d / discount), MathContext.DECIMAL32);
			Order order = new Order.Builder()
					.clientId(clientId)
					.trainerId(trainerId)
					.clientComment(clientComment)
					.startDate(startDate)
					.endDate(endDate)
					.price(price)
					.build();
			orderService.createOrder(order);
			router = new Router(PagePath.ORDER_CREATED, REDIRECT);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}