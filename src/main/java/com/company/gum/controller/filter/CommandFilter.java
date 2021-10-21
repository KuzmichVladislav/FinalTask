package com.company.gum.controller.filter;

import com.company.gum.controller.SessionRequestContent;
import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.CommandType;
import com.company.gum.controller.command.PagePath;
import com.company.gum.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Optional;

import static com.company.gum.controller.command.AttributeName.USER_ROLE;
import static com.company.gum.controller.command.CommandType.*;

/**
 * The Class CommandFilter.
 *
 * @author Vladislav Kuzmich
 */
@WebFilter(urlPatterns = "/controller")
public class CommandFilter implements Filter {

	/**
	 * The role enum map.
	 */
	private final EnumMap<User.UserRole, CommandType[]> roleEnumMap = new EnumMap<>(User.UserRole.class);

	/**
	 * The command types of guest.
	 */
	CommandType[] commandTypesOfGuest = {
			REGISTER,
			VERIFICATION,
			CHANGE_LOCALE,
			LOGIN,
	};

	/**
	 * The command types of client.
	 */
	CommandType[] commandTypesOfClient = {
			CHANGE_LOCALE,
			LOGOUT,
			UPLOAD_IMAGE,
			EDIT_CLIENT_PROFILE,
			CHANGE_PASSWORD,
			NEW_ORDER,
			CREATE_NEW_ORDER,
			DELETE_ORDER_BY_CLIENT,
			SHOW_ALL_ORDERS_BY_CLIENT,
			SHOW_ORDER_BY_CLIENT,
			CREATE_NEW_COMMENT,
			SHOW_ALL_ACTIVE_COMMENTS,
			DELETE_COMMENT,
			EDIT_COMMENT,
			REFILL_MONEY,
			UPDATE_ORDER_STATUS,
			SHOW_ALL_TRAINERS
	};

	/**
	 * The command types of trainer.
	 */
	CommandType[] commandTypesOfTrainer = {
			VERIFICATION,
			CHANGE_LOCALE,
			LOGOUT,
			UPLOAD_IMAGE,
			CHANGE_PASSWORD,
			SHOW_ALL_ORDERS_BY_TRAINER,
			SHOW_ORDER_BY_TRAINER,
			CREATE_NEW_COMMENT,
			SHOW_ALL_ACTIVE_COMMENTS,
			DELETE_COMMENT,
			EDIT_COMMENT,
			EDIT_TRAINER_PROFILE,
			EDIT_DESCRIPTION,
			EDIT_EXPERIENCE,
			EDIT_NUTRITION,
			EDIT_EXERCISES,
			UPDATE_ORDER_STATUS
	};

	/**
	 * The command types of admin.
	 */
	CommandType[] commandTypesOfAdmin = {
			REGISTER_TRAINER,
			CHANGE_LOCALE,
			LOGOUT,
			UPLOAD_IMAGE,
			CHANGE_PASSWORD,
			SHOW_ALL_ORDERS_BY_ADMIN,
			SHOW_ORDER_BY_ADMIN,
			CREATE_NEW_COMMENT,
			SHOW_ALL_ACTIVE_COMMENTS,
			DELETE_COMMENT,
			EDIT_COMMENT,
			EDIT_ADMIN_PROFILE,
			SHOW_USERS,
			DELETE_USER,
			RESTORE_USER,
			SHOW_USER_PROFILE,
			ASSIGN_DISCOUNT
	};

	/**
	 * Do filter.
	 *
	 * @param req   the Servlet request
	 * @param resp  the Servlet response
	 * @param chain the Filter chain
	 * @throws IOException      Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		SessionRequestContent content = new SessionRequestContent(request);
		User.UserRole userRole;
		String userRoleString = (String) content.getSessionAttributeByName(USER_ROLE);
		if (userRoleString != null) {
			userRole = User.UserRole.valueOf(userRoleString);
		} else {
			userRole = User.UserRole.GUEST;
		}
		CommandType commandType;
		String parameter = req.getParameter(AttributeName.COMMAND);
		commandType = CommandType.valueOf(parameter.toUpperCase());
		if (!isAccessAllowed(userRole, commandType)) {
			if (userRole == User.UserRole.GUEST) {
				response.sendRedirect(PagePath.LOGIN);
			}
			if (userRole == User.UserRole.CLIENT) {
				response.sendRedirect(request.getContextPath() + "/" + PagePath.CLIENT_PROFILE);
			}
			if (userRole == User.UserRole.TRAINER) {
				response.sendRedirect(request.getContextPath() + "/" + PagePath.TRAINER_PROFILE);
			}
			if (userRole == User.UserRole.ADMIN) {
				response.sendRedirect(request.getContextPath() + "/" + PagePath.ADMIN_PROFILE);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * Checks if is access allowed.
	 *
	 * @param userRole    the user role
	 * @param commandType the command type
	 * @return true, if access allowed
	 */
	public boolean isAccessAllowed(User.UserRole userRole, CommandType commandType) {
		roleEnumMap.put(User.UserRole.GUEST, commandTypesOfGuest);
		roleEnumMap.put(User.UserRole.CLIENT, commandTypesOfClient);
		roleEnumMap.put(User.UserRole.TRAINER, commandTypesOfTrainer);
		roleEnumMap.put(User.UserRole.ADMIN, commandTypesOfAdmin);
		boolean isAllowed = false;
		CommandType[] types = roleEnumMap.get(userRole);
		Optional<CommandType> optionalCommandType = Arrays.stream(types)
				.filter(x -> x.equals(commandType))
				.findFirst();
		if (optionalCommandType.isPresent()) {
			isAllowed = true;
		}
		return isAllowed;
	}
}