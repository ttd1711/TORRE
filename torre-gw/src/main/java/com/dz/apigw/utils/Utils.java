/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;


/**
 *
 * @author Administrator
 */
@Component
public class Utils implements ILogger {

    public Utils(){}
    
    public JSONObject parseStrToJSONObj(String jSonStr){
        JSONObject ret = null;
        
        JSONParser parser = new JSONParser();
        try{
            ret = (JSONObject) parser.parse(jSonStr);
        }
        catch(Exception ex){
            iLogger.error("[parseStrToJSONObj] Exception is '{}'", ex.getMessage());
        }
        
        return ret;
    }
    
    public String getJSONObjectValueByKey(JSONObject jSonObj, String key, String defaultValue){
        String ret = defaultValue;
        try{
            if(jSonObj!=null){
                ret = (String) jSonObj.get(key);
            }
        }
        catch(Exception ex){
            iLogger.error("[getJSONObjectValueByKey] Exception is '{}'", ex.getMessage());
        }
        
        return ret;
    }
    
    public static String appendString(String sourceStr, String prefixStr, String suffixStr, String preSubStr, String appendStr){        
        return new StringBuilder().append(sourceStr).append(preSubStr).append(prefixStr).append(appendStr).append(suffixStr).toString();
    }
    
    public static String[] singleChars(String s) {
        return s.split("(?!^)");
    }
    
    public static int findSubString(int startPos, int startFoundPos, String[] isdnArr, String searchChar){
        int ret = -1;
        
        for (int i = startPos; i < isdnArr.length; i++) {
            String isdnChar = isdnArr[i];
            if (isdnChar.equalsIgnoreCase(".")){
                
            }
            else if (isdnChar.equalsIgnoreCase(searchChar)){
                ret = i;
                break;
            }
            else{
                if(startFoundPos>=0){
                    ret = -1;
                    break;
                }
            }
        }
        
        return ret;
    }    
    
    public static int findSubStringReserve(int startPos, int startFoundPos, String[] originalArr, String searchChar){
        int ret = -1;
        
        for (int i = startPos; i >= 0; i--) {
            String isdnChar = originalArr[i];
            if (isdnChar.equalsIgnoreCase(".")){
                
            }
            else if (isdnChar.equalsIgnoreCase(searchChar)){
                ret = i;
                break;
            }
            else{
                ret = -1;
                break;
            }
        }
        
        return ret;
    }     
    
}
