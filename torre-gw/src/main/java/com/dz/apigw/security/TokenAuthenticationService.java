/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.security;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.dz.apigw.properties.AppProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {
    @Autowired
    private static AppProperties appProperties;

    static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + appProperties.getToken().getExpiration_time()))
                .signWith(SignatureAlgorithm.HS512, appProperties.getToken().getSecret())
                .compact();
        res.addHeader(appProperties.getToken().getHeader_string(), appProperties.getToken().getPrefix() + " " + JWT);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(appProperties.getToken().getHeader_string());
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(appProperties.getToken().getSecret())
                    .parseClaimsJws(token.replace(appProperties.getToken().getPrefix(), ""))
                    .getBody()
                    .getSubject();

            return user != null
                    ? new UsernamePasswordAuthenticationToken(user, null, emptyList())
                    : null;
        }
        return null;
    }
}
