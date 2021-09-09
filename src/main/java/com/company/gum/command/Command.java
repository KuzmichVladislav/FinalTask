package com.company.gum.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;

public interface Command {

    String execute(SessionRequestContent requestContent) throws CommandException;
}
