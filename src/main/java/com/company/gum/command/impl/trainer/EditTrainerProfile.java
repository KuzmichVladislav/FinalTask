package com.company.gum.command.impl.trainer;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.ErrorMessageKey;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Trainer;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.TrainerServiceImpl;
import com.company.gum.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditTrainerProfile implements Command {

    private static Logger logger = LogManager.getLogger();

    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {
        String page;
        boolean isValid = true;
        try {
            int trainerId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));

            String userName = requestContent.getParameterByName(AttributeName.USER_NAME).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(AttributeName.USER_NAME)
                    : requestContent.getParameterByName(AttributeName.USER_NAME).strip();
            String userSurname = requestContent.getParameterByName(AttributeName.USER_SURNAME).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(AttributeName.USER_SURNAME)
                    : requestContent.getParameterByName(AttributeName.USER_SURNAME).strip();
            String userPhone = requestContent.getParameterByName(AttributeName.USER_PHONE).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(AttributeName.USER_PHONE)
                    : requestContent.getParameterByName(AttributeName.USER_PHONE).strip();
            String userMail = requestContent.getParameterByName(AttributeName.USER_MAIL).strip().equals("")
                    ? (String) requestContent.getSessionAttributeByName(AttributeName.USER_MAIL)
                    : requestContent.getParameterByName(AttributeName.USER_MAIL).strip();

            if (!Validator.checkNameSurname(userName)) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_NAME);
            }
            if (!Validator.checkNameSurname(userSurname) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_SURNAME_NAME);
            }
            if (!Validator.checkPhone(userPhone) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_PHONE);
            }
            if (!Validator.checkMail(userMail) && isValid) {
                isValid = false;
                requestContent.putAttribute(AttributeName.ERR_MESSAGE, ErrorMessageKey.INVALID_EMAIL);
            }

            if (isValid) {
                Trainer trainer = new Trainer.Builder()
                        .id(trainerId)
                        .name(userName)
                        .surname(userSurname)
                        .mail(userMail)
                        .phone(userPhone)
                        .build();

                trainerService.editTrainer(trainer);

                trainer = trainerService.findTrainerById(trainerId);

                requestContent.putSessionAttribute(AttributeName.USER_NAME, trainer.getName());
                requestContent.putSessionAttribute(AttributeName.USER_SURNAME, trainer.getSurname());
                requestContent.putSessionAttribute(AttributeName.USER_PHONE, trainer.getPhone());
                requestContent.putSessionAttribute(AttributeName.USER_MAIL, trainer.getMail());
                page = PagePath.TRAINER_PROFILE;
            } else {
                page = PagePath.TRAINER_PROFILE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
