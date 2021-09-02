package com.company.gum.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;

public interface Command {

	String execute(SessionRequestContent requestContent) throws CommandException, ServiceException;
}
