package com.company.gum.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The Class LocaleFilter.
 *
 * @author Vladislav Kuzmich
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class LocaleFilter implements Filter {

	/**
	 * The Constant LOCALE.
	 */
	public static final String LOCALE = "locale";

	/**
	 * The Constant EN_EN.
	 */
	public static final String EN_EN = "en_EN";

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
		if (httpRequest.getSession(false) != null
				&& httpRequest.getSession(false).getAttribute(LOCALE) == null) {
			httpRequest.getSession().setAttribute(LOCALE, EN_EN);
		}
		chain.doFilter(httpRequest, resp);
	}
}