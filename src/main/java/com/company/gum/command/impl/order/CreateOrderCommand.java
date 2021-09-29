package com.company.gum.command.impl.order;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Duration;
import com.company.gum.entity.Order;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;
import com.company.gum.service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class CreateOrderCommand implements Command {

	private OrderService orderService = OrderServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		Router router;
		try {
			int clientId = (Integer) requestContent.getSessionAttributeByName(USER_ID);
			int discount = (Integer) requestContent.getSessionAttributeByName(USER_DISCOUNT);
			int trainerId = Integer.parseInt(requestContent.getParameterByName(TRAINER_ID));
			String clientComment = requestContent.getParameterByName(COMMENT).strip().replaceAll("<", "")
					.replaceAll(">", "");
			String date = requestContent.getParameterByName(START_DATE);
			LocalDate startDate = LocalDate.parse(date);
			String duration = requestContent.getParameterByName(DURATION);
			Duration training = Duration.values()[Integer.parseInt(duration)];
			LocalDate endDate = startDate.plus(training.day(), ChronoUnit.DAYS);
			BigDecimal price = training.getPrice().multiply(new BigDecimal(1d - 1d / discount), MathContext.DECIMAL32);

			Order order = new Order.Builder().clientId(clientId).trainerId(trainerId).clientComment(clientComment)
					.startDate(startDate).endDate(endDate).price(price).build();

			orderService.createOrder(order);
			router = new Router(PagePath.ORDER_CREATED, REDIRECT);

		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
