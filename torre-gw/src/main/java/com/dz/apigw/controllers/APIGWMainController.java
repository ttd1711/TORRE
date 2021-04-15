/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.controllers;

import static org.springframework.http.HttpStatus.OK;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dz.apigw.exception.AuthenticationException;
import com.dz.apigw.obj.AuthenticationRequest;
import com.dz.apigw.obj.AuthenticationResponse;
import com.dz.apigw.properties.AppProperties;
import com.dz.apigw.services.APIGWMainService;
import com.dz.apigw.utils.IConfig;
import com.dz.apigw.utils.ILogger;
import com.dz.apigw.utils.Utils;


@RestController
public class APIGWMainController 
        extends Thread 
        implements ILogger, IConfig
{
    @Autowired
    APIGWMainService mainService;
    
    @Autowired
    private AppProperties appProperties;
    
    @Autowired
    private Utils utils;

    public APIGWMainController(){
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }    
    
    /*  ===================================================================
    API SERVICE    
    ****LOGIN****
    =================================================================== */    
    @RequestMapping(value = "${apigw.route.authentication.path}", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) {
        AuthenticationResponse ret = mainService.doCreateAuthenticationToken(authRequest);

        return new ResponseEntity<AuthenticationResponse>(ret, OK);
    }    

/*  ===================================================================
    **** MAIN URL ****
    =================================================================== */    
    @RequestMapping(value = "${apigw.route.root.path}", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doMainURLAction(@RequestBody String jSonStr) {
        Object ret = null;
        //parse body string
        iLogger.trace("Remote request body is '{}'",jSonStr);
        try{
            //parrse requets body to json object to get service
            JSONObject requestJSONObj = this.utils.parseStrToJSONObj(jSonStr);
            
            if(requestJSONObj!=null){
                String rqService = utils.getJSONObjectValueByKey(requestJSONObj, "service", "");
                ret = mainService.doMainAction(requestJSONObj, jSonStr, rqService);
            }
        }
        catch(Exception ex){
            iLogger.error("[doMainURLAction] Exception: '{}'",ex.getMessage());
        }

        return new ResponseEntity(ret, OK);
    }
}
