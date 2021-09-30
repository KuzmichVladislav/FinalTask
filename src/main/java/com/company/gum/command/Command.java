package com.company.gum.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;

@FunctionalInterface
public interface Command {

    String IMAGE_SRC_PREFIX = "data:image/jpg;base64,";

    Router execute(SessionRequestContent requestContent) throws CommandException;
}
