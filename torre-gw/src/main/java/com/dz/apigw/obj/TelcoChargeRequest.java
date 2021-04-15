/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.obj;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Administrator
 */
public class TelcoChargeRequest {
    @Id
    private String id;
    
    @Column(nullable = true)
    private String telco;

    @Column(length = 20, nullable = false)
    private String service;

    @Column(length = 100, nullable = false)
    private String session;

    @Column(length = 20, nullable = false)
    private String cp_code;

    @Column(length = 20, nullable = false)
    private String service_code;

    @Column(length = 20, nullable = false)
    private String service_id;

    @Column(length = 20, nullable = false)
    private String package_code;

    @Column(length = 20, nullable = false)
    private String msisdn;

    @Column(length = 20, nullable = false)
    private String account;

    @Column(length = 20, nullable = false)
    private String cmd;

    @Column(length = 30, nullable = false)
    private Date datetime;
    
    @Column(length = 50, nullable = false)
    @JsonProperty("trans_id")
    private String transId;

    @Column(length = 1, nullable = true)
    private int statusProcess;
    
    @Column(length = 5, nullable = true)
    private int error_code;
    
    @Column(length = 20, nullable = true)
    private long price;
    
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

//	public String getTransId() {
//		return transId;
//	}
//
//	public void setTransId(String transId) {
//		this.transId = transId;
//	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStatus_process() {
		return statusProcess;
	}

	public void setStatus_process(int status_process) {
		this.statusProcess = status_process;
	}

	public String getTelco() {
		return telco;
	}

	public void setTelco(String telco) {
		this.telco = telco;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getCp_code() {
		return cp_code;
	}

	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}

	public String getService_code() {
		return service_code;
	}

	public void setService_code(String service_code) {
		this.service_code = service_code;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getPackage_code() {
		return package_code;
	}

	public void setPackage_code(String package_code) {
		this.package_code = package_code;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	

	public String getTrans_id() {
		return transId;
	}

	public void setTrans_id(String trans_id) {
		this.transId = trans_id;
	}

	public int getStatusProcess() {
		return statusProcess;
	}

	public void setStatusProcess(int statusProcess) {
		this.statusProcess = statusProcess;
	}

	@Override
    public String toString() {
        return String.format("TelcoChargeRequest {cp_code='%s', telco='%s', service='%s', service_code='%s', service_id='%s', package_code='%s', msisdn='%s', account='%s',cmd='%s',trans_id='%s',datetime='%s',price='%s'}", cp_code, telco, service, service_code,service_id,package_code,msisdn,account,cmd,transId,datetime,price);
    }
    
}
