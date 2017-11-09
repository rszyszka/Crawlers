/*
 * Copyright (C) 2017 Szysz
 */
package loggers;

import crawler.Logger;
import crawler.Student;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import otherClasses.LoggedStudent;

/**
 *
 * @author Szysz
 */
public class SerializedLogger implements Logger,Closeable {

    FileOutputStream fos;
    ObjectOutputStream out;
    FileInputStream fis;
    ObjectInputStream in;
    
    public List<LoggedStudent> listStudents() throws IOException{
        
        List<LoggedStudent> loggedStudents = new ArrayList<>();
        fis = new FileInputStream("loggedStudents.ser");
        in = new ObjectInputStream(fis);

        try { 
            loggedStudents = (ArrayList<LoggedStudent>) in.readObject();
        } catch (ClassNotFoundException ex) {
        }
        
        return loggedStudents;
    }
    
//    public static void main(String[] args) throws IOException{
//        SerializedLogger log = new SerializedLogger();
//        Student s  = new Student();
//        s.setFirstName("bonio");
//        s.setLastName("maronio");
//        s.setAge(20);
//        s.setMark(3.5);
//        log.log("REMOVED", s);
//        
//        List<LoggedStudent> stud = log.listStudents();
//        for(LoggedStudent st : stud)
//            System.out.println(st.getStatus());
//    }
    
    @Override
    public synchronized void log(String status, Student student) {
       
        LoggedStudent loggedStudent = new LoggedStudent(status,student);
        List<LoggedStudent> loggedStudents = new ArrayList<>();
        try {
            loggedStudents = listStudents();
        } catch (IOException ex) {}
        loggedStudents.add(loggedStudent);
        
        try {
            fos = new FileOutputStream("loggedStudents.ser");
            out = new ObjectOutputStream(fos);
            out.writeObject(loggedStudents);
            out.close();
            fos.close();
        } catch (IOException ex) {}
    }

    @Override
    public synchronized void close() throws IOException {
        try{
            if(fos!=null)
                fos.close();
            if(out!=null)
                out.close();
            if(fis!=null)
                fis.close();
            if(in!=null)
                in.close();
        }catch(IOException ex){}
    }
    
}
