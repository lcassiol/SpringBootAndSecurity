package com.lcassiol.config;

import com.lcassiol.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/books").hasAnyAuthority("ROLE_BOOKS")
                    .antMatchers("/authors").hasAnyAuthority("ROLE_AUTHORS")
                    .antMatchers("/relatorio-equipe").hasAnyAuthority("PG_REL_EQUIPE")
                    .antMatchers("/users").hasAnyAuthority("ROLE_ADMIN")
                    .antMatchers("/user-registration").permitAll()
                    .antMatchers("/console/**").permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/entrar")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/entrar?logout")
                    .permitAll()
                .and()
                    .rememberMe()
                    .userDetailsService(customUserDetailsService);

            //Uncomment if you want to enable h2_console
            //http.csrf().disable();
            //http.headers().frameOptions().disable();

    }

}
