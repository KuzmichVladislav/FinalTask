package com.company.gum.command.impl.trainer;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.TrainerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditDescription implements Command {

    private static Logger logger = LogManager.getLogger();

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;

        try {
            int trainerId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            String description = requestContent.getParameterByName(AttributeName.DESCRIPTION).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(AttributeName.DESCRIPTION)
                    : requestContent.getParameterByName(AttributeName.DESCRIPTION).strip().replaceAll("<", "").replaceAll(">", "");
//            String experience = requestContent.getParameterByName(AttributeName.EXPERIENCE) == null
//                    && requestContent.getParameterByName(AttributeName.EXPERIENCE).strip().equals("")
//                    ? (String) requestContent.getSessionAttributeByName(AttributeName.EXPERIENCE)
//                    : requestContent.getParameterByName(AttributeName.EXPERIENCE).strip().replaceAll("<", "").replaceAll(">", "");

            trainerService.editDescription(trainerId, description);

            requestContent.putSessionAttribute(AttributeName.DESCRIPTION, description);
            page = PagePath.TRAINER_PROFILE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
