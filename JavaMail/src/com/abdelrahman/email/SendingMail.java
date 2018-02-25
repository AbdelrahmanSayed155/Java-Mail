package com.abdelrahman.email;


import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  


public class SendingMail {

	public static void main(String[] args) {


		  String host="smtp.gmail.com";  
		  final String user="abdelrahnamsayed@gmail.com";//change accordingly  
		  final String password="abdo2432";//change accordingly  
		    
		  String to="eng.abdelrahmansayed.cs@gmail.com";//change accordingly  
		  
		   //Get the session object  
		   Properties props = new Properties();  
		    props.put("mail.smtp.host", "smtp.gmail.com");    
	          props.put("mail.smtp.socketFactory.port", "465");    
	          props.put("mail.smtp.socketFactory.class",    
	                    "javax.net.ssl.SSLSocketFactory");    
	          props.put("mail.smtp.auth", "true");    
	          props.put("mail.smtp.port", "465"); 
		     System.out.println("adding infor to Jmail");
		   Session session = Session.getDefaultInstance(props,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		   System.out.println("after  creating session object ");
		   //Compose the message  
		    try {  
		     MimeMessage message = new MimeMessage(session);  
		    // message.setFrom(new InternetAddress(user));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		     message.setSubject("javatpoint");  
		     message.setText("This is simple program of sending email using JavaMail API");  
		     
		     System.out.println("wait sending Message ........ "); 
		    
		     Transport.send(message);  
		  
		     System.out.println("message sent successfully...");  
		   
		     } catch (MessagingException e) {
		    	 System.out.println("MessagingException  for Jmail");
		    	 e.printStackTrace();
		    	 }  
		 }  
	

	

}
