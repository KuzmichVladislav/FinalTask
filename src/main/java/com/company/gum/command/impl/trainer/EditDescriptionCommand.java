package com.company.gum.command.impl.trainer;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.TrainerServiceImpl;

import static com.company.gum.command.AttributeName.DESCRIPTION;
import static com.company.gum.command.AttributeName.USER_ID;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class EditDescriptionCommand implements Command {

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int trainerId = Integer.parseInt(requestContent.getParameterByName(USER_ID));
            String description = requestContent.getParameterByName(DESCRIPTION).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(DESCRIPTION)
                    : requestContent.getParameterByName(DESCRIPTION).strip().replaceAll("<", "").replaceAll(">", "");

            trainerService.editDescription(trainerId, description);

            requestContent.putSessionAttribute(DESCRIPTION, description);
            router = new Router(PagePath.TRAINER_PROFILE, FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
