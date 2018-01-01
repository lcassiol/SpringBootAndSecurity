package com.lcassiol.config;

import com.lcassiol.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsSecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder,
                                PasswordEncoder passwordEncoder,
                                CustomUserDetailsService userDetailsService) throws Exception {
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
