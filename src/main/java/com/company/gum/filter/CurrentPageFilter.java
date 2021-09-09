package com.company.gum.filter;

import com.company.gum.command.AttributeName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Current page filter.
 */
@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String currentPage = httpRequest.getRequestURL().toString();
        if (currentPage.contains("jsp/")) {
            int index = currentPage.indexOf("jsp/");
            currentPage = currentPage.substring(index);
            httpRequest.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);

        } else if (currentPage.contains("controller") && !httpRequest.getParameterMap().isEmpty()) {
            if (httpRequest.getQueryString() != null && !httpRequest.getQueryString().contains("command=change_locale")) {
                int index = currentPage.indexOf("controller");
                currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();
                httpRequest.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
                System.out.println(currentPage);
            }
        }
        //System.out.println(currentPage);
        chain.doFilter(httpRequest, response);
    }
}
