package com.company.gum.filter;

import com.company.gum.command.AttributeName;
import com.company.gum.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/pages/admin/*",
        "/jsp/pages/client/*",
        "/jsp/pages/trainer/*"
})
public class RoleCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
                resp.sendRedirect("/jsp/pages/" + userRole + "/profile.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/" + PagePath.LOGIN);
        }
    }
}
