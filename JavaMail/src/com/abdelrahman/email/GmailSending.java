package com.abdelrahman.email;


import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*; 

public class GmailSending {

	public static void main(String[] args) {

		send("abdelrahnamsayed@gmail.com","abdo2432","eng.abdelrahmansayed.cs@gmail.com","test","Hello Dear this message from my code ");
	}
	
	public static void send(final String From,final String password,String to,String sub,String msg){  
        //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(From,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg); 
         message.saveChanges();
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully .... ");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }  
}  


