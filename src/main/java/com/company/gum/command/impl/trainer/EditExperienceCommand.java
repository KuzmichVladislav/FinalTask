package com.company.gum.command.impl.trainer;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.TrainerServiceImpl;

import static com.company.gum.command.AttributeName.EXPERIENCE;
import static com.company.gum.command.AttributeName.USER_ID;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class EditExperienceCommand implements Command {

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            int trainerId = Integer.parseInt(requestContent.getParameterByName(USER_ID));
            String experience = requestContent.getParameterByName(EXPERIENCE).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(EXPERIENCE)
                    : requestContent.getParameterByName(EXPERIENCE).strip().replaceAll("<", "").replaceAll(">", "");

            trainerService.editExperience(trainerId, experience);

            requestContent.putSessionAttribute(EXPERIENCE, experience);
            router = new Router(PagePath.TRAINER_PROFILE, FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
