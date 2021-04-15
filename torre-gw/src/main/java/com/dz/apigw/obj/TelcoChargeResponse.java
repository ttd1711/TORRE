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
public class TelcoChargeResponse {
    @Id
    @Column(length = 4, nullable = false)
    private int error_code;

    @Column(length = 50, nullable = false)
    private String trans_id;

    public String getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}

	public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    
}

