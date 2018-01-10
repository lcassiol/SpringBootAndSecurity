package com.lcassiol.config;

import com.lcassiol.security.CustomUserDetailsService;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
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

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
