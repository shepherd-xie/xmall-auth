package com.orkva.projects.xmall.auth.filter;

import com.orkva.projects.xmall.auth.AuthenticationUser;
import com.orkva.projects.xmall.auth.common.util.JWTUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * JWTLogoutHandler
 *
 * @author Shepherd Xie
 * @version 2023/8/30
 */
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (!StringUtils.startsWithIgnoreCase(token, "Bearer ")) {
            return;
        }
        AuthenticationUser authenticationUser = JWTUtils.getSubject(token.substring(7), AuthenticationUser.class);
        if (authenticationUser != null) {
            JWTUtils.expired(authenticationUser);
        }
    }
}
