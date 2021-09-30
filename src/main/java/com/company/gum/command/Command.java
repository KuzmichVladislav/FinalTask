package com.company.gum.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;

// TODO: Auto-generated Javadoc
/**
 * The Interface Command.
 */
@FunctionalInterface
public interface Command {

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(SessionRequestContent requestContent) throws CommandException;
}
