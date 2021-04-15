/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dz.apigw.security.AuthorizationIPAddressFilter;
import com.dz.apigw.security.JwtAuthenticationEntryPoint;
import com.dz.apigw.security.JwtAuthorizationTokenFilter;
import com.dz.apigw.security.JwtUserDetailsService;
import com.dz.apigw.utils.IConfig;
import com.dz.apigw.utils.ILogger;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements ILogger, IConfig{

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
//
//    // Custom JWT based security filter
    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;
    
    @Autowired
    AuthorizationIPAddressFilter authorizationIPAddressFilter;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }    
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
               // .antMatchers("/**").hasIpAddress(defUserIpAddress)
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/search/jobs").permitAll()
                .anyRequest().authenticated()
                ;
        httpSecurity
            .addFilterBefore(authorizationIPAddressFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                ;

        // disable page caching
        httpSecurity
            .headers()
            .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
            .cacheControl();
    }

}
