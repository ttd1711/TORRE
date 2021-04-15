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
public class KeepAliveRequest {
    @Id
    @Column(length = 200, nullable = false)
    private String session;

    @Column(length = 20, nullable = false)
    private String service;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
}
