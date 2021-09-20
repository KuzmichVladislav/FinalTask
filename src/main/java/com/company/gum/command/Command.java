package com.company.gum.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;

@FunctionalInterface
public interface Command {

	Router execute(SessionRequestContent requestContent) throws CommandException;
}
