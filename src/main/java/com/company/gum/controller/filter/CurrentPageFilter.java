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
     * Do filter.
     *
     * @param req the req
     * @param resp the resp
     * @param chain the chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String currentPage = httpRequest.getRequestURL().toString();
        if (currentPage.contains("jsp/")) {
            int index = currentPage.indexOf("jsp/");
            currentPage = currentPage.substring(index);
            httpRequest.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);

        } else if (currentPage.contains("controller") && !httpRequest.getParameterMap().isEmpty()
                && httpRequest.getQueryString() != null && !httpRequest.getQueryString().contains("command=change_locale")) {
            int index = currentPage.indexOf("controller");
            currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();
            httpRequest.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
        }
        chain.doFilter(httpRequest, resp);
    }
}
