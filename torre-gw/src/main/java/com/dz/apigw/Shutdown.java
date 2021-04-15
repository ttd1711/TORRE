package com.dz.apigw;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Shutdown {

    private Thread thread = null;

    public Shutdown() {
        thread = new Thread("My thread") {
            public void run() {
                while (true) {
                    System.out.println("[My thread] Sample thread speaking...");
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException ie) {
                        break;
                    }
                }
                System.out.println("[My thread] Stopped");
            }
        };
        thread.start();
    }

    public void stopThread() {
        thread.interrupt();
    }
}
