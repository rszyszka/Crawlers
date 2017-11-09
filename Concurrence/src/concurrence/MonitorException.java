/*
 * Copyright (C) 2017 Szysz
 */
package concurrence;

/**
 *
 * @author Szysz
 */
public class MonitorException extends Exception {
    
    public MonitorException(String msg){
        System.err.println(msg);
    }
    
}
