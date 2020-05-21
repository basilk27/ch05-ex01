package com.mbsystems.ch05ex01.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder    passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider( UserDetailsService userDetailsService,
                                         PasswordEncoder passwordEncoder ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate( Authentication authentication ) throws AuthenticationException {
        String username = authentication.getName();
        String passworrd = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if ( passwordEncoder.matches( passworrd, userDetails.getPassword() ) ) {
            return new UsernamePasswordAuthenticationToken( username, passworrd, userDetails.getAuthorities() );
        }
        else {
            throw new BadCredentialsException( "Invalid Credentials" );
        }
    }

    @Override
    public boolean supports( Class< ? > authenticationType ) {
        return authenticationType.equals( UsernamePasswordAuthenticationToken.class );
    }
}
