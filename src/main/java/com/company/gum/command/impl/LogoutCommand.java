package com.company.gum.command.impl;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;

public class LogoutCommand implements Command {

    @Override
    public String execute(SessionRequestContent requestContent) throws CommandException {

        requestContent.invalidateSession();
        String page = PagePath.INDEX;
        requestContent.putSessionAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }
}
