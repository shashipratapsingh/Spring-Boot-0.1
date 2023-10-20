package com.flipkart.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register","/login").permitAll()
                .antMatchers("/admin/**").hasRole("SUPERADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN","SUPERADMIN","USER")  // ROLE_ADMIN. ROLE_USER , ROLE_ROOT, ROLE_SUPERADMIN
                .and()
                .httpBasic();
    }


    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());  // by custom authentication


        auth.authenticationProvider(customAuthenticationProvider);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}