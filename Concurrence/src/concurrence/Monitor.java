/*
 * Copyright (C) 2017 Szysz
 */
package concurrence;

import crawler.Crawler;
import crawler.CrawlerException;
import crawler.CustomEvent;
import crawler.OrderMode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import crawler.Logger;
import crawler.Student;

/**
 *
 * @author Szysz
 */
public class Monitor {
     
    public final CustomEvent<Student> studentAddedEvent = new CustomEvent<>();
    public final CustomEvent<Student> studentRemovedEvent = new CustomEvent<>();
    
    private final int numberOfThreads;
    private List<Thread> crawlerThreads = new ArrayList<>();
    private List<Thread> logThreads = new ArrayList<>();
    public Logger[] loggers;
    public ParallelLogger parlog;
    
    public Monitor(int n, ParallelLogger parlog){
        if(n<0)
            n=0;
        this.numberOfThreads = n;
        this.parlog=parlog;
        this.loggers = parlog.loggers;
    }
    
    private final List<String> studentPaths = new ArrayList<>();
    
    public void addStudentPath(String path){
        studentPaths.add(path);
    }
   
    public synchronized void start() throws MonitorException, IOException, InterruptedException, CrawlerException{
        
        if(numberOfThreads<studentPaths.size())
            throw new MonitorException("Za malo dostepnych watkow.");

        int i = 1;
        for(String studentPath: studentPaths){
          
            crawlerThreads.add(new CustomThread(studentPath, OrderMode.MARK));
            crawlerThreads.get(i-1).setName("Thread: "+i);
            System.out.println("odpalam: "+crawlerThreads.get(i-1).getName());
            crawlerThreads.get(i-1).start();
            i++;
        }
    }   
      
    public synchronized void cancel() throws InterruptedException{
        
        for(Thread t:crawlerThreads){ 
            t.interrupt();
            t.join();
        }
     
    }
    
    class CustomThread extends Thread{
    
        Crawler craw;
        
        public CustomThread(String adr,OrderMode mode){    
            craw = new Crawler(adr,mode);
            craw.studentAddedEvent.add((source, student)->{
                studentAddedEvent.fire(source, student);
            });
            craw.studentRemovedEvent.add((source,student)->{
                studentRemovedEvent.fire(source, student);
            });
  
        }
        
        @Override
        public void run(){
        
            try {
                craw.run();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                System.out.println(this.getName()+" interrupted");
            } catch (CrawlerException ex) {
                java.util.logging.Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
   
}
