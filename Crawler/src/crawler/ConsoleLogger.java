/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Szysz
 */
public class ConsoleLogger extends UnicastRemoteObject implements Logger {
  
    public ConsoleLogger() throws RemoteException{}
    
    @Override
    public void log(String status, Student student) throws RemoteException{
       System.out.println(status+": "+ student.getMark() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getAge() );
    }
    
}
