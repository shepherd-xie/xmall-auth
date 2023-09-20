package com.orkva.projects.xmall.auth.config;

import com.orkva.projects.xmall.auth.client.filter.JwtVerificationFilter;
import com.orkva.projects.xmall.auth.filter.JwtAuthenticationFilter;
import com.orkva.projects.xmall.auth.filter.JwtLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(registry -> registry.anyRequest().authenticated())
                .logout(config -> config.logoutSuccessHandler(new JwtLogoutSuccessHandler()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager))
                .addFilter(new JwtVerificationFilter(authenticationManager))
        ;
        return http.build();
    }

}
