package gui;

import crawler.ConsoleLogger;
import crawler.Crawler;
import crawler.CrawlerException;
import crawler.ExtremumMode;
import crawler.Logger;
import crawler.MailLogger;
import crawler.OrderMode;
import static crawler.Program.changesDetected;
import java.io.IOException;
import java.util.logging.Level;

/*
 * Copyright (C) 2017 Szysz
 */

/**
 *
 * @author Szysz
 */
public class CrawRunnable implements Runnable {

    @Override
    public void run() {
        
        
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CrawRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        final Logger[] loggers = new Logger[]
            {
                new ConsoleLogger(),
                new GUILogger(),
//                new MailLogger()                
            };
            
            Crawler craw = new Crawler("students.txt",OrderMode.MARK );
            
            craw.iterationStartedEvent.add((iteration)->{
                System.out.println("Iteration: "+ iteration);
                        });
            craw.studentAddedEvent.add((source,student)->{
                 for(Logger el : loggers)
                     el.log("ADDED", student);
                 changesDetected = true;
            });
            craw.studentRemovedEvent.add((source,student)->{
                for(Logger el : loggers)
                     el.log("REMOVED", student);
                changesDetected = true;
            });
            
            craw.iterationFinishedEvent.add((iteration)->{
                
                if(changesDetected){
                
                craw.extractStudents(craw.getMode());
                System.out.println("Age: <"+ craw.extractAge(ExtremumMode.MIN)+", "+craw.extractAge(ExtremumMode.MAX)+">");
                System.out.println("Marks: <"+ craw.extractMarks(ExtremumMode.MIN)+", "+craw.extractMarks(ExtremumMode.MAX)+">");
            
            }
                
            });
            
        try {
            craw.run();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CrawRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CrawRunnable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CrawlerException ex) {
            java.util.logging.Logger.getLogger(CrawRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
