package com.Google.GoogleAuthenticationApp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/response");
                })
                .failureHandler(((request, response, exception) -> {
                    response.sendRedirect("/loginPage.html");
                }))
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true);
    }
}

