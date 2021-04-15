/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.services;

import java.util.Date;
import java.util.Objects;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.dz.apigw.obj.AuthenticationRequest;
import com.dz.apigw.obj.AuthenticationResponse;
import com.dz.apigw.obj.ExceptionResponse;
import com.dz.apigw.obj.KeepAliveRequest;
import com.dz.apigw.obj.KeepAliveResponse;
import com.dz.apigw.obj.TelcoChargeRequest;
import com.dz.apigw.obj.TelcoChargeResponse;
import com.dz.apigw.security.JwtTokenUtil;
import com.dz.apigw.utils.HTTPStatusCode;
import com.dz.apigw.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * @author Administrator
 */

@Service
@Transactional
public class APIGWMainServiceImpl implements APIGWMainService{
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private ObjectMapper mapper;
    
    //FUNCTION MAIN =====================================================================================
    @Override
    public Object doMainAction(JSONObject jSonRequest, String jSonRequestStr, String rqService) {
        iLogger.trace("Service from the request is '{}'",rqService);

        Object ret = null;
        
        try{
            if(jSonRequest!=null && (rqService!=null && !rqService.equalsIgnoreCase(""))){
                //refresh session ------------------------
                if(rqService.equalsIgnoreCase("keep_alive")){
                    ret = (KeepAliveResponse) this.doKeepAlive(mapper.readValue(jSonRequestStr, KeepAliveRequest.class));
                }           
                //your-genome request ------------------------
                else if(rqService.equalsIgnoreCase("your-genome")){
                    ret = (TelcoChargeResponse) this.doTelcoCharge(mapper.readValue(jSonRequestStr, TelcoChargeRequest.class));
                }
                else{
                    
                }
            }
        }
        catch(Exception ex){
            iLogger.error("[doMainAction] Exception: '{}'",ex.getMessage());
        }
        
        if(ret == null){//default response
            ret = new ExceptionResponse();
        }
        
        return ret;
    }
    
    //FUNCTION LOGIN =====================================================================================
    @Override
    public AuthenticationResponse doCreateAuthenticationToken(AuthenticationRequest authRequest) {
        AuthenticationResponse ret = new AuthenticationResponse();
        Objects.requireNonNull(authRequest.getUsername());
        Objects.requireNonNull(authRequest.getPassword());
        try{
            UsernamePasswordAuthenticationToken objAuthenticate = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationManager.authenticate(objAuthenticate);
            // Reload password post-security so we can generate the token
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            ret.setError_code(HTTPStatusCode.ERRCODE_SUCCESS);
            ret.setSession(token);
            ret.setMessage("Login successfully , username="+authRequest.getUsername()+"");
        } catch (DisabledException e) {
            iLogger.error("[doCreateAuthenticationToken] Exception User is disabled! : " + e.getMessage());
            ret.setError_code(HTTPStatusCode.ERRCODE_USERDISABLED);
        } catch (BadCredentialsException e) {
            iLogger.error("[doCreateAuthenticationToken] Exception Bad credentials : " + e.getMessage());
            ret.setError_code(HTTPStatusCode.ERRCODE_BADCREDENTIALS);
        }
        
        return ret;
    }

    
    //FUNCTION REFRESH SESSION =====================================================================================
    @Override
    public KeepAliveResponse doKeepAlive(KeepAliveRequest keepaliveRequest) {
        iLogger.trace("[doKeepAlive] doing refresh token '{}'",keepaliveRequest.getSession());

        KeepAliveResponse ret = new KeepAliveResponse();                
        ret.setError_code(HTTPStatusCode.ERRCODE_NOTSUCCESSED);
        try{
            
            iLogger.trace("[doKeepAlive] expiration time '{}'",jwtTokenUtil.getExpirationDateFromToken(keepaliveRequest.getSession()));
//            final String token = jwtTokenUtil.refreshToken(keepaliveRequest.getSession());
//            iLogger.trace("[doKeepAlive] token after refreshed '{}'",token);
            
            if(!jwtTokenUtil.isTokenExpired(keepaliveRequest.getSession())){
                ret.setError_code(HTTPStatusCode.ERRCODE_SUCCESS);
                ret.setResult(HTTPStatusCode.ERRCODE_TEXT_OK);
            }
        }
        catch(Exception ex){
            iLogger.error("[doKeepAlive] Exception: '{}'", ex.getMessage());
        }
        
        
        return ret;
    }
    
    //FUNCTION TELCO CHARGE =====================================================================================
    @Override
    public TelcoChargeResponse doTelcoCharge(TelcoChargeRequest telcoChargeRequest) {
        iLogger.trace("[doTelcoCharge] doing telco charging. Msg = " + telcoChargeRequest.toString());
        
        TelcoChargeResponse ret = new TelcoChargeResponse();
        ret.setTrans_id(telcoChargeRequest.getTrans_id());  
        ret.setError_code(HTTPStatusCode.ERRCODE_NOTSUCCESSED);
        
        try{
        	//set datetime
        	telcoChargeRequest.setDatetime(new Date());
        	//formalize the request object to get app format for further process
        	telcoChargeRequest = telcoChargingServiceImpl.formalizeTelcoChargeRequest(telcoChargeRequest);
            
        	//verify request parameters & verify credentials
            if(telcoChargingServiceImpl.verifyParameter(telcoChargeRequest) && telcoChargingServiceImpl.verifyRequestCredentials(telcoChargeRequest)){              
            	ret = telcoChargingServiceImpl.processRequest(telcoChargeRequest);                     
            }
            else { //invalid parameter
            	ret.setError_code(HTTPStatusCode.ERRCODE_BADCREDENTIALS);
            }
        }
        catch(Exception ex){
            iLogger.error("[doTelcoCharge] Exception: '{}'", ex.getMessage());
        }
        finally {     	
        }
        
        return ret;    
    }
}
