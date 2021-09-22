package com.company.gum.command.impl.order;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.OrderService;
import com.company.gum.service.impl.OrderServiceImpl;

import static com.company.gum.command.AttributeName.NUTRITION;
import static com.company.gum.command.AttributeName.ORDER_ID;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class EditNutritionCommand implements Command {
    Router router;
    OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        try {
            int orderId = Integer.parseInt(requestContent.getParameterByName(ORDER_ID));
            String nutrition = requestContent.getParameterByName(NUTRITION).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(NUTRITION)
                    : requestContent.getParameterByName(NUTRITION).strip().replaceAll("<", "").replaceAll(">", "");

            orderService.editNutrition(orderId, nutrition);
            requestContent.putSessionAttribute(NUTRITION, nutrition);// TODO: 9/22/2021  не нужен
            router = new Router(PagePath.ORDER_UPDATED, FORWARD);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}