package com.pilot.board.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        log.error("Not Authenticated Request ", authException);
        log.info("authException: {}", authException.getClass());
        log.info("Request Uri : {}", request.getRequestURI());

        if (authException instanceof BadCredentialsException) {
            log.info("BadCredentialsException!!!!!!!!!!!!!!!!!!!!!!");
        }

        response.sendRedirect("/accessDenied");
    }
}
