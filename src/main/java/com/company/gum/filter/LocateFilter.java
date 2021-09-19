package com.company.gum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"})
public class LocateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        if (httpRequest.getSession(false) != null && httpRequest.getSession(false).getAttribute("locale") == null) {
            httpRequest.getSession().setAttribute("locale", "en_EN");
        }
        chain.doFilter(httpRequest, resp);
    }
}
