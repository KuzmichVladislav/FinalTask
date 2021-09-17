package com.company.gum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(urlPatterns = "/*")
public class CharacterEncoding implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        chain.doFilter(req, resp);
    }
}

