/*
 * Copyright (C) 2017 Szysz
 */
package client.application;

import crawler.Logger;
import crawler.Student;

/**
 *
 * @author Szysz
 */
public class CommunicatorLogger implements Logger {

    Client client;
    public CommunicatorLogger(){
        client = new Client("logger", "asd");
    client.connect("localhost", 5000);
    }
    
    @Override
    public synchronized void log(String status, Student student) {
        
        client.sendToAll("["+status+"]"+"  name: "+student.getFirstName()+" "+student.getLastName()+" mark: "+student.getMark()+" age: "+student.getAge()+"\n");
    }
    
}
