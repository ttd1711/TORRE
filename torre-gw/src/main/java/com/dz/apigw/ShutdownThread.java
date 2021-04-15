/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dz.apigw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Administrator
 */
public class ShutdownThread extends Thread {

    private Shutdown shutdown = null;
    private ApplicationContext context;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ShutdownThread(Shutdown shutdown) {
        super();
        this.shutdown = shutdown;
    }

    public ShutdownThread() {
        super();
    }

    public void run() {
        printTerminated();
    }

    public void printTerminated() {
        logger.info("===============================================");
        logger.info("*                   API GW                    *");
        logger.info("*             Copyright by Dzungt             *");
        logger.info("***********************************************");
        logger.info("*                                             *");
        logger.info("*               Program Stopped!              *");
        logger.info("*                                             *");
        logger.info("===============================================");
    }
}
