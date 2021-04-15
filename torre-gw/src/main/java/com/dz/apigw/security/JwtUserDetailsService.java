/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dz.apigw.entity.User;
import com.dz.apigw.properties.AppProperties;
import com.dz.apigw.utils.ILogger;

@Service
public class JwtUserDetailsService implements UserDetailsService, ILogger {
    
    @Autowired
    private AppProperties appProperties;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        iLogger.info("[JwtUserDetailsService] load info of user '{}'", username);
        //get password by username
        try{
            if(username.equalsIgnoreCase(this.appProperties.getUser().getUsername())){
                user = new User(this.appProperties.getUser().getUsername(), this.appProperties.getUser().getPassword());
            }
//            user = userRepository.findByUsername(username);
//            String user_password = this.getPasswordByUsername(username);
//            if(user_password != null){//found user
//                user = userRepository.findByUsername(username);
////                user = new User();
////                user.setUsername(username);
////                user.setPassword(user_password);
//            }
        }
        catch(Exception ex){
            iLogger.error("[loadUserByUsername] Exception: " + ex.getMessage());
        }

        if (user == null) {
            iLogger.info("[loadUserByUsername] No user found with username '{}'", username);
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            iLogger.info("[loadUserByUsername] Found user with username '{}'", username);
            return JwtUserFactory.create(user);
        }
    }
    
}
