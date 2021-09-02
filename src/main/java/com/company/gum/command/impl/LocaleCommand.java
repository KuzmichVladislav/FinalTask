package com.company.gum.command.impl;

import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.ParameterName;
import com.company.gum.controller.SessionRequestContent;

public class LocaleCommand implements Command {

	@Override
	public String execute(SessionRequestContent requestContent) {
		String locale = requestContent.getParameterByName(ParameterName.LOCALE);
		requestContent.putSessionAttribute(ParameterName.LOCALE, locale);
		String page = (String) requestContent.getSessionAttributeByName(ParameterName.CURRENT_PAGE);
		if (page == null) {
			page = PagePath.INDEX;
		}
		return page;
	}

}
