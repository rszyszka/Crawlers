/*
 * Copyright (C) 2017 Szysz
 */
package loggers;

import crawler.Logger;
import crawler.Student;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.logging.Level;

/**
 *
 * @author Szysz
 */
public class TextLogger implements Logger, Closeable {

    private Calendar now;
    private String time;
    private Writer writer;
    private final String fileName;
    
    public TextLogger(){
        this.fileName = "log.txt";
    }
    public TextLogger(String fileName){
        this.fileName = fileName;
    }
    
    @Override
    public synchronized void log(String status, Student student) {
        
        now = Calendar.getInstance();
        time = String.format("%1$tH:%1$tM:%1$tS.%1$tL", now);
        
        File log = new File(fileName);
        try{
            if(!log.exists()){
                log.createNewFile();
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log,true),"UTF-8")); 
            writer.append(time+"  ["+status+"]  mark: "+student.getMark()+
                    ", name: "+student.getFirstName()+" "+
                    student.getLastName()+", age: "+student.getAge());
            writer.append(System.lineSeparator());
            writer.close();
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TextLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void close() throws IOException {
        if(writer!=null){
            writer.close();
            writer = null;
        } 
    }
    
}
