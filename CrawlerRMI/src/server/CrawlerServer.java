/*
 * Copyright (C) 2017 Szysz
 */
package server;

import common.RMICrawlerProxy;
import crawler.OrderMode;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Szysz
 */
public class CrawlerServer {

    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy","file:/C:/Users/Szysz/Documents/NetBeansProjects/ProjektyJava/CrawlerRMI/security.policy");       
            System.setSecurityManager(new SecurityManager());
            
            RMICrawlerProxy craw = new RMICrawlerProxy("students.txt", OrderMode.MARK);
            LocateRegistry.createRegistry(1099);
            Naming.rebind("crawler", craw);

        } catch (MalformedURLException | RemoteException e) {
            System.err.println(e);
        }
    }

}
