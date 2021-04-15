/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import com.dz.apigw.repository.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {
    @Value("${apigw.user.username}")
    private String defUserName;
    @Value("${apigw.user.password}")
    private String defPassword;
    
    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public void run(String[] args) {
//        log.info("-------------------------------");
//        log.info("Insert default users      ");
//        log.info("-------------------------------");
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(this.defPassword);       
//        log.info(hashedPassword);
//        System.out.println("11111111111:" + hashedPassword);
//        User defUser = new User(this.defUserName, hashedPassword);
//        userRepository.save(defUser);
//        log.info("-------------------------------");
//        log.info("Finding all users");
//        log.info("-------------------------------");
//        for (User user : userRepository.findAll()) {
//            log.info(user.toString());
//        }
    }
}
