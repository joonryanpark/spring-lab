package com.spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.formLogin()
            .loginPage("/login")
            .and()
            .authorizeRequests()
            .antMatchers("/spitter/me").hasRole("SPITTER")
            .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
            .anyRequest().permitAll();

  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().
            withUser(User
              .withDefaultPasswordEncoder()
              .username("user")
              .password("password")
              .roles("USER").build());
  }
}
