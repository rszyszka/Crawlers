/*
 * Copyright (C) 2017 Szysz
 */
package otherClasses;

import crawler.Student;
import java.io.Serializable;

/**
 *
 * @author Szysz
 */
public class LoggedStudent extends Student implements Serializable{
    
    private long time;
    private Status status;
    
    public LoggedStudent(String status, Student student){
        time = System.currentTimeMillis() / 1000L;
        if(status.equals("ADDED"))
            this.status = Status.ADDED;
        else
            this.status = Status.REMOVED;
        
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.age = student.getAge();
        this.mark = student.getMark(); 
    }

    public LoggedStudent() {
       this.time = System.currentTimeMillis() / 1000L;
       this.status = null;
    }
    
    public long getTime() {
        return time;
    }

    public Status getStatus() {
        return status;
    }  

    public void setTime(long time) {
        this.time = time;
    }

    public void setStatus(String status) {
        if(status.equals("ADDED"))
            this.status = Status.ADDED;
        else
            this.status = Status.REMOVED;
    }

    
}
