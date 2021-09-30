package com.company.gum.command.impl.admin;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.User;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.UserService;
import com.company.gum.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static com.company.gum.command.Router.RouterType.FORWARD;

public class ShowUsersCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {
        Router router;
        try {
            List<User> users = new ArrayList<>(userService.findAllUser());
            router = new Router(PagePath.SHOW_ALL_USERS, FORWARD);
            requestContent.putAttribute(AttributeName.USERS, users);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
