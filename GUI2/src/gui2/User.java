/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Szysz
 */
public class User {
    
    private String username;
    private byte[] password;
    private String address;
    private String sex;
    private byte[] salt;
    private int age;
    private int id = 1;
    
    public User(){
        username = null;
        password = null;
        address = null;
        sex = null;
        age = 0;
        this.salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
    }
    
    public User(String username, String password, String address, String sex, int age){
        this.salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        
        this.username= username;
        try {
            this.password=hash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.address = address;
        this.sex = sex;
        this.age = age;
    }
    
        public User(String username){
        this.salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        
        this.username= username;
    }
    
    private byte[] hash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), this.salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return f.generateSecret(spec).getEncoded();
    }
    
    public void saveUser(){
        Base64.Encoder enc = Base64.getEncoder();
        Properties p=new Properties();
        
        byte[] toBeHashed = new byte[this.password.length+this.salt.length];
        System.arraycopy(this.password, 0, toBeHashed, 0,this.password.length);
        System.arraycopy(this.salt, 0, toBeHashed, this.password.length, this.salt.length);
        
	p.setProperty("username", this.username);
	p.setProperty("password", enc.encodeToString(toBeHashed));
	p.setProperty("age", Integer.toString(this.age));
	p.setProperty("sex", this.sex);
	p.setProperty("address", this.address);
//        p.setProperty("salt", enc.encodeToString(this.salt));
			
        OutputStream output = null;
        
        try {
            output = new FileOutputStream("config.ini");
            p.store(output,null);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(output != null){
                try {
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        try {
            this.password=hash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public byte[] getSalt() {
        return salt;
    } 
    
    @Override
    public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((username == null) ? 0 : username.hashCode());
            return result;
    }
    
    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (obj == null)
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            User other = (User) obj;
            if (username == null) {
                    if (other.username != null)
                            return false;
            } else if (!username.equals(other.username))
                    return false;
            return true;
    }   
    
}
