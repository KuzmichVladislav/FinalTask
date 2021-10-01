package com.company.gum.controller.command.impl;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;

import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

// TODO: Auto-generated Javadoc
/**
 * The Class LogoutCommand.
 */
public class LogoutCommand implements Command {

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     */
    @Override
    public Router execute(SessionRequestContent requestContent) {

        requestContent.invalidateSession();
        return new Router(PagePath.INDEX, REDIRECT);
    }
}
