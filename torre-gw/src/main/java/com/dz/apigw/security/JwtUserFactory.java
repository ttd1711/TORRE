/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.security;

import com.dz.apigw.entity.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
//                user.getFirstname(),
//                user.getLastname(),
//                user.getEmail(),
                user.getPassword()
//                mapToGrantedAuthorities(user.getAuthorities()),
//                user.getEnabled(),
//                user.getLastPasswordResetDate()
        );
    }
//
//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
//        return authorities.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
//                .collect(Collectors.toList());
//    }
}