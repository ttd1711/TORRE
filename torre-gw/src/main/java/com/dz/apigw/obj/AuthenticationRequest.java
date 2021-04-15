/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.obj;


import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author Administrator
 */
public class AuthenticationRequest {
    @Id
    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String service;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return String.format("ObjAuthenticationRequest {username='%s', password='%s', service='%s'}", username, password, service);
    }
    
}

