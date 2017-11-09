/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import crawler.Logger;
import crawler.Student;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;

/**
 *
 * @author Szysz
 */
public class Gui2Logger extends UnicastRemoteObject implements Logger {
    
    MainGui gui;
    
    public Gui2Logger(MainGui gui) throws RemoteException{
		this.gui=gui;
	}
    
    @Override
    public void log(String status, Student student) throws RemoteException{
       
        switch(status){
            case "ADDED":
                Platform.runLater(()->{
                    gui.addStudent(student);
                });
                break;
            case "REMOVED":
                Platform.runLater(()->{
                    gui.removeStudent(student);
                });        
                break;
            default:
                break;
        }
    }
    
    
}
