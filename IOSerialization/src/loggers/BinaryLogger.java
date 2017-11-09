/*
 * Copyright (C) 2017 Szysz
 */
package loggers;

import crawler.Logger;
import crawler.Student;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import otherClasses.LoggedStudent;

/**
 *
 * @author Szysz
 */
public class BinaryLogger implements Logger, Closeable {
    
    DataInputStream dis;
    DataOutputStream dos;
    FileInputStream fis;
    FileOutputStream fos;

    @Override
    public synchronized void log(String status, Student student) {
        
        LoggedStudent stud = new LoggedStudent(status, student);
        try {
            fos = new FileOutputStream("binaryStudents.bin", true);
            dos = new DataOutputStream(fos);
            
            dos.writeLong(stud.getTime());       
            dos.writeDouble(stud.getMark());
            dos.writeInt(stud.getAge());
            dos.writeUTF(stud.getFirstName());
            dos.writeUTF(stud.getLastName());
            dos.writeUTF(status);
            
        } catch (FileNotFoundException ex) {
            System.err.println("binaryStudents.bin : File not found");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(BinaryLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public synchronized void close() throws IOException {
        if(dis!=null)
            dis.close();
        if(fis!=null)
            fis.close();
        if(dos!=null)
            dos.close();
        if(fos!=null)
            fos.close();
    }
    public synchronized List<LoggedStudent> listStudents() throws IOException {
	fis=new FileInputStream("binaryStudents.bin");
	dis=new DataInputStream(fis);
	List<LoggedStudent> students=new ArrayList<>();
	while (true){
		LoggedStudent stud=new LoggedStudent();
		try {
                    stud.setTime(dis.readLong());
                    stud.setMark(dis.readDouble());
                    stud.setAge(dis.readInt());
                    stud.setFirstName(dis.readUTF());
                    stud.setLastName(dis.readUTF());
                    stud.setStatus(dis.readUTF());
		} catch (IOException e) {
			break;
		}
		students.add(stud);
	}
	fis.close();
	dis.close();
	return students;
    }
    
    public static void main(String[] args) throws IOException{
        BinaryLogger log = new BinaryLogger();
        Student s = new Student();
        s.setFirstName("bonio");
        s.setLastName("maronio");
        s.setAge(20);
        s.setMark(3.5);
        log.log("ADDED", s);
 
        
        List<LoggedStudent> list = log.listStudents();
        System.out.println(list.size());
        for(LoggedStudent l:list){
            System.out.println(l.getTime()+"  "+l.getStatus()+" mark: "+l.getMark()+" name: "+l.getFirstName()+" "+l.getLastName()+
                    " age: "+l.getAge());
        }
        
    }
    
}
