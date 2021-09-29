package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;

import static com.company.gum.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.command.AttributeName.LOCALE;
import static com.company.gum.command.Router.RouterType.FORWARD;

public class LocaleCommand implements Command {

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
