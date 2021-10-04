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
 * The Class RoleCheckFilter.
 *
 * @author Vladislav Kuzmich
 */
@WebFilter(urlPatterns = {"/jsp/admin/*", "/jsp/client/*", "/jsp/trainer/*"})
public class RoleCheckFilter implements Filter {

    /**
     * Do filter.
     *
     * @param request the request
     * @param response the response
     * @param chain the chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String userRole = (String) session.getAttribute(AttributeName.USER_ROLE);
        if (userRole != null) {
            userRole = userRole.toLowerCase();
            String currentPage = req.getRequestURL().toString();

            if (currentPage.contains("/" + userRole + "/")) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/jsp/" + userRole + "/profile.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/" + PagePath.LOGIN);
        }
    }
}
