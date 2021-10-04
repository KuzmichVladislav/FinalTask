package com.company.gum.controller.command.impl.client;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Trainer;
import com.company.gum.model.service.TrainerService;
import com.company.gum.model.service.impl.TrainerServiceImpl;

import java.util.List;

import static com.company.gum.controller.command.AttributeName.TRAINERS;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class NewOrderCommand.
 *
 * @author Vladislav Kuzmich
 */
public class NewOrderCommand implements Command {

    /**
     * The trainer service.
     */
    private final TrainerService trainerService = TrainerServiceImpl.getInstance();

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
            List<Trainer> trainers = trainerService.findAllActiveTrainer();
            requestContent.putAttribute(TRAINERS, trainers);
            router = new Router(PagePath.CREATE_ORDER, FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
