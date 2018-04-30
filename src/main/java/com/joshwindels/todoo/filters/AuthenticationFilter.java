package com.joshwindels.todoo.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joshwindels.todoo.dos.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements Filter {

    @Autowired
    CurrentUser currentUser;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
        boolean isLoginRequest = httpServletRequest.getRequestURL().toString().contains("/todoo/login");
        boolean isRegistrationRequest = httpServletRequest.getRequestURL().toString().contains("/todoo/register");
        if ((!isLoginRequest) && (!isRegistrationRequest) && (currentUser.getId() == null)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("/todoo/login");
        }
        else {
            chain.doFilter(request, response);
        }
        return;
    }

    @Override
    public void destroy() {
    }
}
