/*
 * Copyright (C) 2017 Szysz
 */
package common;

import crawler.Crawler;
import crawler.CrawlerException;
import crawler.CustomEvent;
import crawler.EmptyEvent;
import crawler.ExtremumMode;
import crawler.ICustomListener;
import crawler.IEmptyListener;
import crawler.OrderMode;
import crawler.Student;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Szysz
 */
public class RMICrawlerProxy extends UnicastRemoteObject implements RMICrawler {

    private final Crawler crawler;

    public RMICrawlerProxy(String adr, OrderMode mode) throws RemoteException {
        crawler = new Crawler(adr, mode);
    }

    @Override
    public String getAdress() throws RemoteException {
        return crawler.getAdress();
    }

    @Override
    public OrderMode getMode() throws RemoteException {
        return crawler.getMode();
    }

    @Override
    public void setAdress(String adress) throws RemoteException {
        crawler.setAdress(adress);
    }

    @Override
    public void setMode(OrderMode mode) throws RemoteException {
        crawler.setMode(mode);
    }

    @Override
    public void postCancel() throws RemoteException {
        crawler.postCancel();
    }

    @Override
    public void run() throws RemoteException, IOException, InterruptedException, CrawlerException {
        crawler.run();
    }

    @Override
    public List<Student> extractStudents(OrderMode mode) throws RemoteException {
        return crawler.extractStudents(mode);
    }

    @Override
    public double extractMarks(ExtremumMode mode) throws RemoteException {
        return crawler.extractMarks(mode);
    }

    @Override
    public int extractAge(ExtremumMode mode) throws RemoteException {
        return crawler.extractAge(mode);
    }

    @Override
    public EmptyEvent getIterationStartedEvent() throws RemoteException {
        return crawler.iterationStartedEvent;
    }

    @Override
    public EmptyEvent getIterationFinishedEvent() throws RemoteException {
        return crawler.iterationFinishedEvent;
    }

    @Override
    public CustomEvent<Student> getStudentAddedEvent() throws RemoteException {
        return crawler.studentAddedEvent;
    }

    @Override
    public CustomEvent<Student> getStudentRemovedEvent() throws RemoteException {
        return crawler.studentRemovedEvent;
    }

    @Override
    public CustomEvent<Student> getStudentNotModifiedEvent() throws RemoteException {
        return crawler.studentNotModifiedEvent;
    }

    @Override
    public void setIterationStartedEvent(EmptyEvent ev) throws RemoteException {
        crawler.iterationStartedEvent = ev;
    }

    @Override
    public void setIterationFinishedEvent(EmptyEvent ev) throws RemoteException {
        crawler.iterationFinishedEvent = ev;
    }

    @Override
    public void setStudentAddedEvent(CustomEvent<Student> ev) throws RemoteException {
        crawler.studentAddedEvent=ev;
    }

    @Override
    public void setStudentRemovedEvent(CustomEvent<Student> ev) throws RemoteException {
        crawler.studentRemovedEvent = ev;
    }

    @Override
    public void setStudentNotModifiedEvent(CustomEvent<Student> ev) throws RemoteException {
        crawler.studentNotModifiedEvent = ev;
    }

    @Override
    public void setA(int a) throws RemoteException{crawler.iterationStartedEvent.a = a;}
    @Override
    public int getA() throws RemoteException{return crawler.iterationStartedEvent.a;}   

    @Override
    public void addAdded(ICustomListener<Student> listener) throws RemoteException {
        crawler.studentAddedEvent.add(listener);
    }

    @Override
    public void addRemoved(ICustomListener<Student> listener) throws RemoteException {
        crawler.studentRemovedEvent.add(listener);
    }

    @Override
    public void addNotModified(ICustomListener<Student> listener) throws RemoteException {
        crawler.studentNotModifiedEvent.add(listener);
    }

    @Override
    public void addStarted(IEmptyListener listener) throws RemoteException {
        crawler.iterationStartedEvent.add(listener);
    }

    @Override
    public void addFinished(IEmptyListener listener) throws RemoteException {
        crawler.iterationFinishedEvent.add(listener);
    }

    @Override
    public void removeAdded(ICustomListener<Student> listener) throws RemoteException {
        crawler.studentAddedEvent.remove(listener);
    }

    @Override
    public void removeRemoved(ICustomListener<Student> listener) throws RemoteException {
        crawler.studentRemovedEvent.remove(listener);
    }

    @Override
    public void removeNotModified(ICustomListener<Student> listener) throws RemoteException {
        crawler.studentNotModifiedEvent.remove(listener);
    }

    @Override
    public void removeStarted(IEmptyListener listener) throws RemoteException {
        crawler.iterationStartedEvent.remove(listener);
    }

    @Override
    public void removeFinished(IEmptyListener listener) throws RemoteException {
        crawler.iterationFinishedEvent.remove(listener);
    }

    
}
