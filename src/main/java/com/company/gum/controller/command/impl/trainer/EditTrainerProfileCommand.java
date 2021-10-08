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
import com.company.gum.model.validator.FormValidator;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

public class EditTrainerProfileCommand implements Command {

    private final TrainerService trainerService = TrainerServiceImpl.getInstance();
    private final FormValidator validator = FormValidator.getInstance();


    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        boolean isValid = true;
        try {
            int trainerId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));

            String userName = requestContent.getParameterByName(USER_NAME).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(USER_NAME)
                    : requestContent.getParameterByName(USER_NAME).strip();
            String userSurname = requestContent.getParameterByName(USER_SURNAME).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(USER_SURNAME)
                    : requestContent.getParameterByName(USER_SURNAME).strip();
            String userPhone = requestContent.getParameterByName(USER_PHONE).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(USER_PHONE)
                    : requestContent.getParameterByName(USER_PHONE).strip();
            String userMail = requestContent.getParameterByName(USER_MAIL).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(USER_MAIL)
                    : requestContent.getParameterByName(USER_MAIL).strip();

            if (!validator.checkNameSurname(userName)) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "invalid.name");
            }
            if (!validator.checkNameSurname(userSurname) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "invalid.surname");
            }
            if (!validator.checkPhone(userPhone) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "invalid.phone");
            }
            if (!validator.checkMail(userMail) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "invalid.email");
            }

            if (isValid) {
                Trainer trainer = new Trainer.Builder().id(trainerId).name(userName).surname(userSurname).mail(userMail)
                        .phone(userPhone).build();

                trainerService.editTrainer(trainer);

                trainer = trainerService.findTrainerById(trainerId);

                requestContent.putSessionAttribute(USER_NAME, trainer.getName());
                requestContent.putSessionAttribute(USER_SURNAME, trainer.getSurname());
                requestContent.putSessionAttribute(USER_PHONE, trainer.getPhone());
                requestContent.putSessionAttribute(USER_MAIL, trainer.getMail());
                router = new Router(PagePath.TRAINER_PROFILE, REDIRECT);
            } else {
                router = new Router(PagePath.TRAINER_PROFILE, FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
