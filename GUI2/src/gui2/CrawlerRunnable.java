/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import client.application.CommunicatorLogger;
import common.RMICrawler;
import crawler.ConsoleLogger;
import crawler.Crawler;
import crawler.CrawlerException;
import crawler.CustomEvent;
import crawler.EmptyEvent;
import crawler.ExtremumMode;
import crawler.ICustomListener;
import crawler.IEmptyListener;
import crawler.Logger;
import crawler.MailLogger;
import crawler.OrderMode;
import static crawler.Program.changesDetected;
import crawler.Student;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.*;
import java.rmi.RemoteException;
import java.util.logging.Level;
import loggers.BinaryLogger;
import loggers.CompressedLogger;
import loggers.SerializedLogger;
import loggers.TextLogger;

/**
 *
 * @author Szysz
 */
public class CrawlerRunnable implements Runnable {

    MainGui gui;

    public CrawlerRunnable(MainGui gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CrawlerRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
          try {          
        final Logger[] loggers = new Logger[]{
            new ConsoleLogger(),
            new Gui2Logger(gui), 
//            new MailLogger(),
//            new TextLogger(),
//            new CompressedLogger(),
//            new SerializedLogger(),
//            new BinaryLogger(),
//            new CommunicatorLogger()
        };
//        Crawler craw = new Crawler("students.txt", OrderMode.MARK);

 
            
            RMICrawler craw;

            System.setProperty("java.security.policy","file:/C:/Users/Szysz/Documents/NetBeansProjects/ProjektyJava/GUI2/security.policy");
            
            System.setSecurityManager(new SecurityManager());
            
            craw = (RMICrawler) Naming.lookup("crawler");
            
            craw.addStarted((IEmptyListener & Serializable)(iteration)->{
                System.out.println("Iteration: " + iteration);
            });
            
            craw.addAdded((ICustomListener<Student> & Serializable)(source,student)->{
                try {
                   for (Logger el : loggers)
                    el.log("ADDED", student);
                } catch (RemoteException ex) {
                    System.err.println(ex);
                }

                changesDetected = true;            
            });
            
            craw.addRemoved((ICustomListener<Student>&Serializable)(source,student)->{
                try {
                   for (Logger el : loggers)
                    el.log("REMOVED", student);
                } catch (RemoteException ex) {
                    System.err.println(ex);
                }
                
                
                changesDetected = true;            
            });
            
            craw.addFinished((IEmptyListener&Serializable)(iteration)->{
                if (changesDetected) {
                    try {
                        craw.extractStudents(craw.getMode());
                        System.out.println("Age: <" + craw.extractAge(ExtremumMode.MIN) + ", " + craw.extractAge(ExtremumMode.MAX) + ">");
                        System.out.println("Marks: <" + craw.extractMarks(ExtremumMode.MIN) + ", " + craw.extractMarks(ExtremumMode.MAX) + ">");
                    } catch (RemoteException ex) {
                    }
                }            
            });
            

//            EmptyEvent iterationStarted = craw.getIterationStartedEvent();
//            iterationStarted.add((IEmptyListener & Serializable) (iteration) -> {
//                System.out.println("Iteration: " + iteration);
//            });
//            craw.setIterationStartedEvent(iterationStarted);
//
//            CustomEvent<Student> studentAdded = craw.getStudentAddedEvent();
//            studentAdded.add((ICustomListener<Student> & Serializable) (source, student) -> {
//                for (Logger el : loggers) {
//                    el.log("ADDED", student);
//                }
//                changesDetected = true;
//            });
//            craw.setStudentAddedEvent(studentAdded);
//
//            CustomEvent<Student> studentRemoved = craw.getStudentRemovedEvent();
//            studentRemoved.add((ICustomListener<Student> & Serializable) (source, student) -> {
//                for (Logger el : loggers) {
//                    el.log("REMOVED", student);
//                }
//                changesDetected = true;
//            });
//            craw.setStudentRemovedEvent(studentRemoved);
//
//            EmptyEvent iterationFinished = craw.getIterationFinishedEvent();
//            iterationFinished.add((IEmptyListener & Serializable) (iteration) -> {
//
//                if (changesDetected) {
//                    try {
//                        craw.extractStudents(craw.getMode());
//                        System.out.println("Age: <" + craw.extractAge(ExtremumMode.MIN) + ", " + craw.extractAge(ExtremumMode.MAX) + ">");
//                        System.out.println("Marks: <" + craw.extractMarks(ExtremumMode.MIN) + ", " + craw.extractMarks(ExtremumMode.MAX) + ">");
//                    } catch (RemoteException ex) {
//                    }
//                }
//
//            });
//            craw.setIterationFinishedEvent(iterationFinished);

            try {
                craw.run();
            } catch (IOException | InterruptedException | CrawlerException ex) {
                java.util.logging.Logger.getLogger(CrawlerRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.err.println(ex);
        }
    }
}
