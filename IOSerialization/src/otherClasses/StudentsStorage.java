/*
 * Copyright (C) 2017 Szysz
 */
package otherClasses;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 *
 * @author Szysz
 */
public class StudentsStorage {
 
    FileInputStream fis;
    FileOutputStream fos;
    DataInputStream dis;
    DataOutputStream dos;
    String fileName;
    File file;
    
    public StudentsStorage() throws IOException{
        fileName = "studentsPhotos.bin";
        file = new File(fileName);
        if(!file.isFile()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("Couldn't create a file");
            }
        }
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        }
        dos = new DataOutputStream(fos);
        for(int i = 0; i<20;i++){
            dos.writeLong(-1);
        }
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.skipBytes(8*20);
            long tmp = raf.getFilePointer();
            raf.seek(0);
            raf.writeLong(tmp);
        }
        dos.close();
        fos.close();
        fos = null;
        dos = null;
    }
    
    public void writeImage(byte[] image) throws IOException{
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(0);
        
        long tmp=0,tmp1,tmp2=0;
        for(int i = 0 ; i <20 ; i++){
            tmp1 = tmp2;
            tmp = raf.getFilePointer();
            tmp2 = raf.readLong();
            if(tmp2==-1){
                raf.seek(tmp1);
                break;
            }    
        }
        raf.write(image);
        tmp2 = raf.getFilePointer();
        raf.seek(tmp);
        raf.writeLong(tmp2);
        raf.close();
        
    }
    
    public byte[] readImage(int index) throws IOException{

          RandomAccessFile raf = new RandomAccessFile(file, "rw");
          raf.seek(0);
          raf.skipBytes(index*8);
          long tmp1 = raf.readLong();
          long tmp2 = raf.readLong();
          int size = (int)(tmp2-tmp1);
          System.out.println(size);
          byte[] bytes = new byte[size];
          raf.seek(tmp1);
          raf.read(bytes);
          raf.close();
          return bytes;
    }
    
    public static void main(String[] args) throws IOException{
        StudentsStorage ss = new StudentsStorage();   
        FileInputStream[] fis = new FileInputStream[4];
        byte[][] images = new byte[4][];
        
        for(int i  = 0; i<4; i++){
            fis[i] = new FileInputStream("legit_"+i+".jpg");
            images[i] = new byte [fis[i].available()];
            fis[i].read(images[i]);
            ss.writeImage(images[i]);
            fis[i].close();
        }
        
        byte[] image1 = ss.readImage(1);
        byte[] image0 = ss.readImage(0);
        byte[] image3 = ss.readImage(3);
        byte[] image2 = ss.readImage(2);
        
        if(Arrays.equals(image2, images[2]) && Arrays.equals(image1, images[1])&& Arrays.equals(image0, images[0])&& Arrays.equals(image3, images[3]))
            System.out.println("OK");
    }

}
