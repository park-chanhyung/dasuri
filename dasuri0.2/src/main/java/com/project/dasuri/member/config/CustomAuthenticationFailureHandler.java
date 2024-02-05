package com.project.dasuri.member.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        System.out.println("@#@#@#@#  -----  Authentication Exception: " + exception.getMessage());

        String errorMessage = URLEncoder.encode(exception.getMessage(), "UTF-8");

        // 리디렉션 URL
        String redirectUrl = "/login?error=true&message=" + errorMessage;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
