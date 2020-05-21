package com.mbsystems.ch05ex01.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final String userName;
    private final String password;
    private final String authority;

    public SecurityUser( String userName, String password, String authority ) {
        this.userName = userName;
        this.password = password;
        this.authority = authority;
    }

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities() {
        return List.of( () -> authority );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
