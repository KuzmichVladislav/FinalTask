package com.company.gum.controller.filter;

import com.company.gum.controller.command.AttributeName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The Class CurrentPageFilter.
 *
 * @author Vladislav Kuzmich
 */
@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {

	/**
	 * The Constant JSP.
	 */
	public static final String JSP = "jsp/";

	/**
	 * The Constant CONTROLLER.
	 */
	public static final String CONTROLLER = "controller";

	/**
	 * The Constant COMMAND_CHANGE_LOCALE.
	 */
	public static final String COMMAND_CHANGE_LOCALE = "command=change_locale";

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
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String currentPage = httpRequest.getRequestURL().toString();
		if (currentPage.contains(JSP)) {
			int index = currentPage.indexOf(JSP);
			currentPage = currentPage.substring(index);
			httpRequest.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
		} else if (currentPage.contains(CONTROLLER) && !httpRequest.getParameterMap().isEmpty()
				&& httpRequest.getQueryString() != null && !httpRequest.getQueryString().contains(COMMAND_CHANGE_LOCALE)) {
			int index = currentPage.indexOf(CONTROLLER);
			currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();
			httpRequest.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
		}
		chain.doFilter(httpRequest, resp);
	}
}
