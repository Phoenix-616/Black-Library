/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nskgoserver;

import java.io.IOException;
import java.io.*;
import java.util.Scanner;


/**
 *
 * @author phoenix
 */
public class NskGoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DBWorker dbw = new DBWorker("jdbc:mysql://127.0.0.1:3306/nskgo", "admin", "admin");
            
            Thread netThread = new Thread(new Runnable(){
                @Override
                public void run() {
                    NetWorker net = new NetWorker(dbw);
                    try {
                        net.activate();
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            });
            
            
            netThread.setDaemon(true);
            netThread.start();
            
            Scanner sc = new Scanner(System.in);
            
            while(true) {
                String str = sc.nextLine();
                if ((str != null) && (str.equals("q"))) {
                    break;
                } else {
                    System.err.println("q to exit");
                }
            }

            
            dbw.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
