package com.company.gum.controller.command.impl.trainer;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Trainer;
import com.company.gum.model.service.TrainerService;
import com.company.gum.model.service.impl.TrainerServiceImpl;

import java.util.List;

import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class ShowAllTrainerCommand.
 *
 * @author Vladislav Kuzmich
 */
public class ShowAllTrainerCommand implements Command {

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
        try {
            List<Trainer> trainers = trainerService.findAllActiveTrainer();
            requestContent.putAttribute(AttributeName.TRAINERS, trainers);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(PagePath.SHOW_ALL_TRAINERS, FORWARD);
    }
}
