package com.orkva.projects.xmall.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orkva.projects.xmall.auth.common.util.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * JWTAuthenticationFilter
 *
 * @author Shepherd Xie
 * @version 2023/8/29
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserDetails userDetails;
        try {
            userDetails = objectMapper.readValue(request.getInputStream(), UserDetails.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(userDetails.getUsername(), userDetails.getPassword());
        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWTUtils.createToken((UserDetails) authResult.getPrincipal());
        response.addHeader("Authorization", "Bearer " + token);
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }

}
