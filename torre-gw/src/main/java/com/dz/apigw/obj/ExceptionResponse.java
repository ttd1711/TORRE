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
public class ExceptionResponse {
    @Id
    @Column(length = 3, nullable = false)
    private String error_code;

    @Column(length = 100, nullable = false)
    private String message;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}

