/*
 * Copyright (C) 2017 Szysz
 */
package concurrence;

import crawler.ConsoleLogger;
import crawler.CrawlerException;
import crawler.Logger;
import gui.MainGui;
import java.io.IOException;

/**
 *
 * @author Szysz
 */
public class Main {
    
    public static void main(String[] args) throws MonitorException, IOException, InterruptedException, CrawlerException{
    
        String p1 = "http://home.agh.edu.pl/~ggorecki/IS_Java/students.txt";

        final Logger[] loggers = new Logger[]{
            new ConsoleLogger()
        };
                
        ParallelLogger parlog = new ParallelLogger(loggers);
        MainGui gui = new MainGui();
        Monitor monitor = new Monitor(10, parlog);
        for(int i = 1; i <10; i++)
            monitor.addStudentPath("students"+i+".txt");
        monitor.addStudentPath(p1);
        monitor.studentAddedEvent.add((source,student)->{
            parlog.log("ADDED", student);
        });
        monitor.studentRemovedEvent.add((source,student)->{
            parlog.log("REMOVED", student);
        });
     
        Thread tr = new Thread(() -> {
            try {
                monitor.start();
                Thread.sleep(30000);
                monitor.cancel();
            } catch (MonitorException | IOException | InterruptedException | CrawlerException ex) {  
            }
        });   
        tr.start();  
    }
    
}
