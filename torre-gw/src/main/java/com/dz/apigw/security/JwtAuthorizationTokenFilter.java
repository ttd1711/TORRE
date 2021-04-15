/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dz.apigw.properties.AppProperties;
import com.dz.apigw.utils.IConfig;
import com.dz.apigw.utils.ILogger;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter implements ILogger, IConfig{
    @Autowired
    private AppProperties appProperties;

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final String tokenHeader;

    public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, @Value("${apigw.token.header_string}") String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        iLogger.trace("Processing authentication for '{}' ...", request.getRequestURL());

        //checking remote ip address
//        iLogger.trace("Checking Remote Ip Adress '{}' for the request", request.getRemoteAddr());
//        if(this.jwtTokenUtil.ipFilter(this.defUserIpAddress, request.getRemoteAddr())){ // ip is valid        
//            iLogger.trace("Remote Ip Adress is '{}'", this.getIpAddress(request));
//            iLogger.trace("this.tokenHeader is '{}'", this.tokenHeader);
            final String requestHeader = request.getHeader(this.tokenHeader);
            iLogger.debug("The request header is '{}'", requestHeader);

            iLogger.debug("Looking for the header start with '{}'", appProperties.getToken().getPrefix());
            String username = null;
            String authToken = null;
            if (requestHeader != null && requestHeader.startsWith(appProperties.getToken().getPrefix())) {
                authToken = requestHeader.substring(appProperties.getToken().getPrefix().length() + 1);
                try {
                    username = jwtTokenUtil.getUsernameFromToken(authToken);
                } catch (IllegalArgumentException e) {
                    iLogger.error("an error occured during getting username from token", e);
                } catch (ExpiredJwtException e) {
                    iLogger.warn("the token is expired and not valid anymore", e);
                }
            } else {
                iLogger.warn("couldn't find " + appProperties.getToken().getPrefix() + " string, will ignore the header");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                iLogger.debug("checking authentication for user '{}'", username);
                iLogger.debug("security context was null, so authorizating user");

                // It is not compelling necessary to load the use details from the database. You could also store the information
                // in the token and read it from it. It's up to you ;)
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
                // the database compellingly. Again it's up to you ;)
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    iLogger.info("authorizated user '{}', setting security context", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }        
//        }
//        else{ // ip is not allowed
//            iLogger.error("Ip Adress '{}' is not allowed", request.getRemoteAddr());            
//        }

        chain.doFilter(request, response);
    }
}