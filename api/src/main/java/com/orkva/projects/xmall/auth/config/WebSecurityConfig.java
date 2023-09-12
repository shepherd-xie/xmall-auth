package com.orkva.projects.xmall.auth.config;

import com.orkva.projects.xmall.auth.filter.JWTAuthenticationFilter;
import com.orkva.projects.xmall.auth.filter.JWTLogoutSuccessHandler;
import com.orkva.projects.xmall.auth.filter.JWTVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfig
 *
 * @author Shepherd Xie
 * @version 2023/9/12
 */
@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(config -> config.disable())
                .authorizeHttpRequests(registry -> registry.anyRequest().authenticated())
                .logout(config -> config.logoutSuccessHandler(new JWTLogoutSuccessHandler()))
                .addFilter(new JWTAuthenticationFilter())
                .addFilter(new JWTVerifyFilter(http.getSharedObject(AuthenticationManager.class)))
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
