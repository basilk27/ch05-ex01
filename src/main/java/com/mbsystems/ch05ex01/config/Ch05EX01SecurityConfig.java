package com.mbsystems.ch05ex01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class Ch05EX01SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public Ch05EX01SecurityConfig( AuthenticationProvider authenticationProvider ) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.authenticationProvider( this.authenticationProvider );
    }
}
