package com.company.gum.command.order;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
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

public class CreateOrder implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        try {
            int clientId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);
            int discount = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_DISCOUNT);
            int trainerId = Integer.parseInt(requestContent.getParameterByName(AttributeName.TRAINER_ID));
            String clientComment = requestContent.getParameterByName(AttributeName.COMMENT).strip().replaceAll("<script>", "").replaceAll("</script>", "");
            String date = requestContent.getParameterByName(AttributeName.START_DATE);
            LocalDate startDate = LocalDate.parse(date);
            String duration = requestContent.getParameterByName(AttributeName.DURATION);
            Duration training = Duration.values()[Integer.parseInt(duration)];
            LocalDate endDate = startDate.plus(training.day(), ChronoUnit.DAYS);
            BigDecimal price = training.getPrice().multiply(new BigDecimal(1d - 1d / discount), MathContext.DECIMAL32);

            Order order = new Order.Builder().clientId(clientId)
                    .trainerId(trainerId)
                    .clientComment(clientComment)
                    .startDate(startDate)
                    .endDate(endDate)
                    .price(price)
                    .build();

            orderService.createOrder(order);
            page = PagePath.ORDER_CREATED;

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
