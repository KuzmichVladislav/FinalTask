package com.company.gum.controller.command;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.exception.CommandException;

/**
 * The Interface Command.
 *
 * @author Vladislav Kuzmich
 */
@FunctionalInterface
public interface Command {

    /**
     * The image src prefix.
     */
    String IMAGE_SRC_PREFIX = "data:image/jpg;base64,";

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(SessionRequestContent requestContent) throws CommandException;
}
