/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.services;

import org.json.simple.JSONObject;

import com.dz.apigw.obj.AuthenticationRequest;
import com.dz.apigw.obj.AuthenticationResponse;
import com.dz.apigw.obj.KeepAliveRequest;
import com.dz.apigw.obj.KeepAliveResponse;
import com.dz.apigw.obj.TelcoChargeRequest;
import com.dz.apigw.obj.TelcoChargeResponse;
import com.dz.apigw.utils.IConfig;
import com.dz.apigw.utils.ILogger;

/**
 *
 * @author Administrator
 */
public interface APIGWMainService extends ILogger, IConfig {
//    @Async("asyncExecutor")
    Object doMainAction(JSONObject jSonRequest, String jSonRequestStr, String rqService);
    AuthenticationResponse doCreateAuthenticationToken(AuthenticationRequest authRequest);
    KeepAliveResponse doKeepAlive(KeepAliveRequest keepaliveRequest);
    TelcoChargeResponse doTelcoCharge(TelcoChargeRequest telcoChargeRequest);
}
