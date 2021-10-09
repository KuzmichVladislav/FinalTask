package com.company.gum.controller.command.impl.admin;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.Command;
import com.company.gum.controller.command.Router;
import com.company.gum.exception.CommandException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.service.AdminService;
import com.company.gum.model.service.impl.AdminServiceImpl;

import static com.company.gum.controller.command.AttributeName.CURRENT_PAGE;
import static com.company.gum.controller.command.AttributeName.USER_ID;
import static com.company.gum.controller.command.Router.RouterType.FORWARD;

/**
 * The Class DeleteUserCommand.
 *
 * @author Vladislav Kuzmich
 */
public class DeleteUserCommand implements Command {

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
        try {
            int userId = Integer.parseInt(requestContent.getParameterByName(USER_ID));
            adminService.deleteUser(userId);
            router = new Router((String) requestContent.getSessionAttributeByName(CURRENT_PAGE), FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
