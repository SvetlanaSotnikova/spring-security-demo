package ru.gb.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        authentication.getAuthorities().forEach(authority -> {
            try {
                if (authority.getAuthority().equals("admin")) {
                    response.sendRedirect("/admin");
                } else if (authority.getAuthority().equals("reader")) {
                    response.sendRedirect("/reader");
                } else {
                    response.sendRedirect("/"); // перенаправление для других ролей или без ролей
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
