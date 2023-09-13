package com.orkva.projects.xmall.auth.config;

import com.orkva.projects.xmall.auth.filter.JWTAuthenticationFilter;
import com.orkva.projects.xmall.auth.filter.JWTLogoutSuccessHandler;
import com.orkva.projects.xmall.auth.filter.JWTVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        http
                .csrf(config -> config.disable())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(registry -> registry.anyRequest().authenticated())
                .logout(config -> config.logoutSuccessHandler(new JWTLogoutSuccessHandler()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager))
                .addFilter(new JWTVerifyFilter(authenticationManager))
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
