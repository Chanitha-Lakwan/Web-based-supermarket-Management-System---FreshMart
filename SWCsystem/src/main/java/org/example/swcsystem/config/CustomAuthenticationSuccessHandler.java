package org.example.swcsystem.config;

import org.example.swcsystem.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
        
        String emailOrUsername = authentication.getName();
        String emailToCheck = emailOrUsername;

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            String userEmail = ((User) principal).getEmail();
            if (userEmail != null && !userEmail.isBlank()) {
                emailToCheck = userEmail;
            }
        }

        String normalized = emailToCheck == null ? "" : emailToCheck.toLowerCase();
        if (normalized.endsWith("fresh.com")) {
            response.sendRedirect("/products-categories");
        } else {
            response.sendRedirect("/freshmart");
        }
    }
}

