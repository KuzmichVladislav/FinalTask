package com.company.gum.command.impl.admin;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.service.TrainerService;
import com.company.gum.service.impl.ClientServiceImpl;
import com.company.gum.service.impl.TrainerServiceImpl;

import static com.company.gum.command.Router.RouterType.FORWARD;

public class ShowUserProfileCommand implements Command {

    private ClientService clientService = ClientServiceImpl.getInstance();
    private TrainerService trainerService = TrainerServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        User user;
        try {
            int userId = Integer.parseInt(requestContent.getParameterByName(AttributeName.USER_ID));
            User.UserRole role = User.UserRole.valueOf(requestContent.getParameterByName(AttributeName.USER_ROLE).toUpperCase());

            switch (role) {
                case CLIENT:
                    user = clientService.findClientById(userId);
                    break;
                case TRAINER:
                    user = trainerService.findTrainerById(userId);
                    break;
                default:
                    throw new CommandException();// TODO: 9/28/2021 невозможная комманда (отладочная)
            }

            requestContent.putAttribute(AttributeName.USER, user);
            router = new Router(PagePath.USER_PROFILE, FORWARD);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
