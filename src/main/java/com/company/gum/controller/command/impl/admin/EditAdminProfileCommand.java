package com.company.gum.controller.command.impl.admin;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.PagePath;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Admin;
import com.company.gum.model.service.AdminService;
import com.company.gum.model.service.impl.AdminServiceImpl;
import com.company.gum.model.validator.FormValidator;

import static com.company.gum.controller.command.AttributeName.*;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;
import static com.company.gum.controller.command.Router.RouterType.REDIRECT;

/**
 * @author Vladislav Kuzmich The Class EditAdminProfileCommand.
 */
public class EditAdminProfileCommand implements Command {

    /**
     * The admin service.
     */
    private final AdminService adminService = AdminServiceImpl.getInstance();

    /**
     * Execute.
     *
     * @param requestContent the request content
     * @return the router
     * @throws CommandException the command exception
     */
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

            if (!FormValidator.checkNameSurname(userName)) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "invalid.name");
            }
            if (!FormValidator.checkNameSurname(userSurname) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "invalid.surname");
            }
            if (!FormValidator.checkMail(userMail) && isValid) {
                isValid = false;
                requestContent.putAttribute(ERROR_MESSAGE, "invalid.email");
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
