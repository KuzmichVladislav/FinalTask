package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;

import static com.company.gum.command.Router.RouterType.REDIRECT;

public class LogoutCommand implements Command {

    @Override
    public Router execute(SessionRequestContent requestContent) throws CommandException {

        requestContent.invalidateSession();
//         requestContent.putSessionAttribute(AttributeName.CURRENT_PAGE, page); // TODO: 9/20/2021
        return new Router(PagePath.INDEX, REDIRECT);
    }
}
