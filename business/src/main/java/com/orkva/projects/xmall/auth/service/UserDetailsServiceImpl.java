package com.orkva.projects.xmall.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserDetailServiceImpl
 *
 * @author Shepherd Xie
 * @version 2023/9/12
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User
                .withUsername(username)
                .password(NoOpPasswordEncoder.getInstance().encode("pass"))
                .authorities("admin")
                .build();
    }

}
