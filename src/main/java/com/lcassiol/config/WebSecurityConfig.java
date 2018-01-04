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

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/books").hasAnyAuthority("PG_PROJETOS")
                    .antMatchers("/authors").hasAnyAuthority("PG_REL_CUSTOS")
                    .antMatchers("/relatorio-equipe").hasAnyAuthority("PG_REL_EQUIPE")
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

    }

}
