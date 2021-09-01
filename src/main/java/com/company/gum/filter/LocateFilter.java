package com.company.gum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/*"}, dispatcherTypes = {
    DispatcherType.FORWARD,
    DispatcherType.INCLUDE,
    DispatcherType.REQUEST
})
public class LocateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (httpRequest.getSession(false) != null && httpRequest.getSession(false).getAttribute("locale") == null) {
            httpRequest.getSession().setAttribute("locale", "en_EN");
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
