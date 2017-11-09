/*
 * Copyright (C) 2017 Szysz
 */
package common;

import crawler.CrawlerException;
import crawler.CustomEvent;
import crawler.EmptyEvent;
import crawler.ExtremumMode;
import crawler.ICustomListener;
import crawler.IEmptyListener;
import crawler.OrderMode;
import crawler.Student;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Szysz
 */
public interface RMICrawler extends Remote {
    
    public void setA(int a) throws RemoteException;
    public int getA() throws RemoteException;
    
    public void setIterationStartedEvent(EmptyEvent ev) throws RemoteException;
    public void setIterationFinishedEvent(EmptyEvent ev) throws RemoteException;
    public void setStudentAddedEvent(CustomEvent<Student> ev) throws RemoteException;
    public void setStudentRemovedEvent(CustomEvent<Student> ev) throws RemoteException;
    public void setStudentNotModifiedEvent(CustomEvent<Student> ev) throws RemoteException;
    
    public void addAdded(ICustomListener<Student> listener) throws RemoteException;
    public void addRemoved(ICustomListener<Student> listener) throws RemoteException;
    public void addNotModified(ICustomListener<Student> listener) throws RemoteException;
    public void addStarted(IEmptyListener listener) throws RemoteException;
    public void addFinished(IEmptyListener listener) throws RemoteException;
    public void removeAdded(ICustomListener<Student> listener) throws RemoteException;
    public void removeRemoved(ICustomListener<Student> listener) throws RemoteException;
    public void removeNotModified(ICustomListener<Student> listener) throws RemoteException;
    public void removeStarted(IEmptyListener listener) throws RemoteException;
    public void removeFinished(IEmptyListener listener) throws RemoteException;
    
    public EmptyEvent getIterationStartedEvent() throws RemoteException;
    
    public EmptyEvent getIterationFinishedEvent() throws RemoteException;
    
    public CustomEvent<Student> getStudentAddedEvent() throws RemoteException;
    
    public CustomEvent<Student> getStudentRemovedEvent() throws RemoteException;
    
    public CustomEvent<Student> getStudentNotModifiedEvent() throws RemoteException;

    public String getAdress() throws RemoteException;

    public OrderMode getMode() throws RemoteException;

    public void setAdress(String adress) throws RemoteException;

    public void setMode(OrderMode mode) throws RemoteException;

    public void postCancel() throws RemoteException;

    public void run() throws RemoteException, IOException, InterruptedException, CrawlerException;

    public List<Student> extractStudents(OrderMode mode) throws RemoteException;

    public double extractMarks(ExtremumMode mode) throws RemoteException;

    public int extractAge(ExtremumMode mode) throws RemoteException;

}
