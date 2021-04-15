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
public class AuthenticationResponse {
    @Id
    @Column(length = 5, nullable = false)
    private int error_code;

    @Column(length = 100, nullable = false)
    private String session;

    @Column(length = 100, nullable = false)
    private String message;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}

