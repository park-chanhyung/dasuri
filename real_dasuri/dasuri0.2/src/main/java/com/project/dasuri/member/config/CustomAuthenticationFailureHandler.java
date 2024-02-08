package com.project.dasuri.member.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
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

        String errorMessage;
        if (exception instanceof BadCredentialsException) {   //비밀번호가 일치하지 않을 때 던지는 예외
            errorMessage = "id_password";
        } else if (exception instanceof InternalAuthenticationServiceException) {// 존재하지 않는 아이디일 때 던지는 예외
            errorMessage = "비활성화된 계정입니다.<br>"+exception.getMessage();
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) { //인증 요구가 거부됐을 때 던지는 예외
            errorMessage = "default";
        } else if (exception instanceof LockedException) { //인증 거부 - 잠긴 계정
            errorMessage = "default";
        } else if (exception instanceof DisabledException) { //인증 거부 - 계정 비활성화
            errorMessage = "default";
        } else if (exception instanceof AccountExpiredException) { //인증 거부 - 계정 유효기간 만료
            errorMessage = "default";
        } else if (exception instanceof CredentialsExpiredException) { //인증 거부 - 비밀번호 유효기간 만료
            errorMessage = "default";
        } else {
            errorMessage = "default";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        // 리디렉션 URL
        String redirectUrl = "/login?error=true&message=" + errorMessage;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
