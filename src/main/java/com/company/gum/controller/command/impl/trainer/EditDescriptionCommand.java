package com.company.gum.controller.command.impl.trainer;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.TrainerService;
import com.company.gum.model.service.impl.TrainerServiceImpl;
import com.company.gum.model.util.UtilClass;

import static com.company.gum.controller.command.AttributeName.DESCRIPTION;
import static com.company.gum.controller.command.AttributeName.USER_ID;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class EditDescriptionCommand.
 *
 * @author Vladislav Kuzmich
 */
public class EditDescriptionCommand implements Command {

    /** The trainer service. */
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
            int trainerId = Integer.parseInt(requestContent.getParameterByName(USER_ID));
            String description = UtilClass.getInstance().getStringFromDescription(requestContent, DESCRIPTION);

            trainerService.editDescription(trainerId, description);

            requestContent.putSessionAttribute(DESCRIPTION, description);
            router = new Router(PagePath.TRAINER_PROFILE, FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}