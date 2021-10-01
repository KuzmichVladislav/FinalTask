package com.company.gum.controller.command.impl;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;

import static com.company.gum.controller.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.controller.command.AttributeName.LOCALE;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

// TODO: Auto-generated Javadoc
/**
 * The Class LocaleCommand.
 */
public class LocaleCommand implements Command {

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     */
    @Override
    public Router execute(SessionRequestContent requestContent) {
        String locale = requestContent.getParameterByName(LOCALE);
        requestContent.putSessionAttribute(LOCALE, locale);
        Router router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        if (requestContent.getSessionAttributeByName(CURRENT_PAGE) == null) {
            router = new Router(PagePath.INDEX, FORWARD);
        }
        return router;
    }

}
