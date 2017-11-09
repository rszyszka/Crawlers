/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Szysz
 */
public interface Logger extends Remote {
    
    void log( String status, Student student ) throws RemoteException;
    
}
