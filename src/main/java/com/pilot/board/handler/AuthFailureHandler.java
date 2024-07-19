package com.pilot.board.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        String message = URLEncoder.encode(getExceptionMessage(exception), StandardCharsets.UTF_8);
        setDefaultFailureUrl("/error-page?error=" + message);

        super.onAuthenticationFailure(request, response, exception);
    }

    private String getExceptionMessage(AuthenticationException exception) {
        AuthenticationTypes authenticationTypes = AuthenticationTypes.findOf(exception.getClass().getSimpleName());
        return authenticationTypes.getValue();
    }
}
