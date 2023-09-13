package com.orkva.projects.xmall.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AuthenticationUser
 *
 * @author Shepherd Xie
 * @version 2023/9/13
 */
@Data
public class AuthenticationUser implements UserDetails {
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<AuthenticationRule> authorities;

    public List<GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            return Collections.emptyList();
        }
        return authorities.stream()
                .map(AuthenticationRule::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
