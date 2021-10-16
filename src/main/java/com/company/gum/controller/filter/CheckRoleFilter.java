package com.company.gum.controller.filter;

import com.company.gum.controller.command.AttributeName;
import com.company.gum.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The Class CheckRoleFilter.
 *
 * @author Vladislav Kuzmich
 */
@WebFilter(urlPatterns = {"/jsp/admin/*", "/jsp/client/*", "/jsp/trainer/*"})
public class CheckRoleFilter implements Filter {

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
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);
		String userRole = (String) session.getAttribute(AttributeName.USER_ROLE);
		if (userRole != null) {
			userRole = userRole.toLowerCase();
			String currentPage = request.getRequestURL().toString();
			if (currentPage.contains("/" + userRole + "/")) {
				chain.doFilter(req, resp);
			} else {
				response.sendRedirect(request.getContextPath() + "/jsp/" + userRole + "/profile.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/" + PagePath.LOGIN);
		}
	}
}