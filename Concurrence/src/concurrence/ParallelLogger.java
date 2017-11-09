/*
 * Copyright (C) 2017 Szysz
 */
package concurrence;

import crawler.Logger;
import crawler.Student;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Szysz
 */
public class ParallelLogger implements Logger{
    
    public BlockingQueue<Student> studentAddedQueue , studentRemovedQueue;
    public Logger[] loggers;
    public Thread parAddedThread, parRemovedThread;
    
    public ParallelLogger(Logger[] loggers){
        this.loggers = loggers;
        studentAddedQueue = new LinkedBlockingQueue<>();
        studentRemovedQueue = new LinkedBlockingQueue<>();

    }

    @Override
    public void log(String status, Student student) {
        
        try{
            switch(status){
                case "ADDED" :
                    studentAddedQueue.put(student);
                    break;
                case "REMOVED":
                    studentRemovedQueue.put(student);
                    break;
                default:
                    break;
            }
        }catch(InterruptedException ex){}
        
        parAddedThread = new Thread(()->{
            for(Logger el:loggers){
                try{
                    el.log("ADDED", studentAddedQueue.poll(2,TimeUnit.SECONDS));
                }catch(InterruptedException | NullPointerException ex){}
            }
        });        
        parAddedThread.start();
        parRemovedThread = new Thread(()->{
            for(Logger el:loggers){
                try{
                    el.log("REMOVED", studentRemovedQueue.poll(2,TimeUnit.SECONDS));
                }catch(InterruptedException | NullPointerException ex){}
            }
        });        
        parRemovedThread.start();
    }
    
}
