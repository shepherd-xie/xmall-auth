package com.orkva.projects.xmall.auth.filter;

import com.orkva.projects.xmall.auth.AuthenticationUser;
import com.orkva.projects.xmall.auth.common.util.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * JWTVerifyFilter
 *
 * @author Shepherd Xie
 * @version 2023/8/29
 */
public class JWTVerifyFilter extends BasicAuthenticationFilter {
    public JWTVerifyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (!StringUtils.startsWithIgnoreCase(token, "Bearer ")) {
            super.doFilterInternal(request, response, chain);
            return;
        }

        AuthenticationUser authenticationUser = JWTUtils.getSubject(token.substring(7), AuthenticationUser.class);
        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken
                .authenticated(authenticationUser.getUsername(), null, authenticationUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        chain.doFilter(request, response);
    }

}
