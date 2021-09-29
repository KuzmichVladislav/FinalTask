package com.company.gum.command.impl.admin;

import com.company.gum.command.AttributeName;
import com.company.gum.command.Command;
import com.company.gum.command.PagePath;
import com.company.gum.command.Router;
import com.company.gum.controller.SessionRequestContent;
import com.company.gum.entity.Admin;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.AdminService;
import com.company.gum.service.impl.AdminServiceImpl;
import com.company.gum.util.Validator;

import static com.company.gum.command.AttributeName.*;
import static com.company.gum.command.ErrorMessageKey.*;
import static com.company.gum.command.Router.RouterType.FORWARD;
import static com.company.gum.command.Router.RouterType.REDIRECT;

public class EditAdminProfileCommand implements Command {

	private AdminService adminService = AdminServiceImpl.getInstance();

	@Override
	public Router execute(SessionRequestContent requestContent) throws CommandException {
		Router router;
		boolean isValid = true;
		try {
			int adminId = (Integer) requestContent.getSessionAttributeByName(AttributeName.USER_ID);

			String userName = requestContent.getParameterByName(USER_NAME).strip().equals("")
					? (String) requestContent.getSessionAttributeByName(USER_NAME)
					: requestContent.getParameterByName(USER_NAME).strip();
			String userSurname = requestContent.getParameterByName(USER_SURNAME).strip().equals("")
					? (String) requestContent.getSessionAttributeByName(USER_SURNAME)
					: requestContent.getParameterByName(USER_SURNAME).strip();
			String userMail = requestContent.getParameterByName(USER_MAIL).strip().equals("")
					? (String) requestContent.getSessionAttributeByName(USER_MAIL)
					: requestContent.getParameterByName(USER_MAIL).strip();

			if (!Validator.checkNameSurname(userName)) {
				isValid = false;
				requestContent.putAttribute(ERR_MESSAGE, INVALID_NAME);
			}
			if (!Validator.checkNameSurname(userSurname) && isValid) {
				isValid = false;
				requestContent.putAttribute(ERR_MESSAGE, INVALID_SURNAME_NAME);
			}
			if (!Validator.checkMail(userMail) && isValid) {
				isValid = false;
				requestContent.putAttribute(ERR_MESSAGE, INVALID_EMAIL);
			}

			if (isValid) {
				Admin admin = new Admin.Builder().id(adminId).name(userName).surname(userSurname).mail(userMail)
						.build();

				adminService.editAdmin(admin);

				admin = adminService.findAdminById(adminId);

				requestContent.putSessionAttribute(USER_NAME, admin.getName());
				requestContent.putSessionAttribute(USER_SURNAME, admin.getSurname());
				requestContent.putSessionAttribute(USER_MAIL, admin.getMail());
				router = new Router(PagePath.ADMIN_PROFILE, REDIRECT);
			} else {
				router = new Router(PagePath.ADMIN_PROFILE, FORWARD);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return router;
	}
}
