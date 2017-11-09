/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Szysz
 */
public class MailLogger implements Logger{

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final String FROM = "projectytesty@gmail.com";
    private static final String PASSWORD = "testy123";
    private static final String TO = "projectytesty@gmail.com";
   
    
    @Override
    public void log(String status, Student student) {
        
        
        try{
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.auth", "true");
            Session mailSession = Session.getDefaultInstance(props);
            
            // Tworzenie wiadomości email
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(status);
            message.setContent(student.getMark() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getAge(), "text/plain; charset=ISO-8859-2");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            Transport transport = mailSession.getTransport();
            transport.connect(HOST, PORT, FROM, PASSWORD);
            // wysłanie wiadomości
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        }catch(MessagingException mex){
            mex.printStackTrace();
        }
    }
    
}
