package com.abdelrahman.email;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailClient {
	private String userName;
	private String password;
	private String host;
	private String from;
	private String body;
	private String subject;
	private List<String> to;
	private int connectionTimeout;
	private int socketTimeout;

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public EmailClient() {
		to = new ArrayList<String>();
	}

	/**
	 * @param string
	 */
	public void setFrom(String string) {
		from = string;
	}

	/**
	 * @param string
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param string
	 */
	public void setBody(String string) {
		body = string;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string) {
		subject = string;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}

	public void addRecipient(String toEmail) {
		if (!(toEmail == null && "".equals(toEmail)))
			to.add(toEmail);
	}

	public void sendEmail() throws AddressException, MessagingException {

	try{
		
		System.out.print("connectionTimeout:"+connectionTimeout+" and socketTimeout:"+socketTimeout);
		// Get system properties
		Properties props = System.getProperties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "false");

		// connection timeout
		if (connectionTimeout > 0)
			props.put("mail.smtp.connectiontimeout", connectionTimeout+"");

		// socket timeout
		if (socketTimeout > 0)
			props.put("mail.smtp.timeout", socketTimeout+"");

		Session session = Session.getInstance(props, null);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));

		// add recipient
		for (int i = 0; i < to.size(); i++) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					String.valueOf(to.get(i))));
		}

		message.setSubject(subject, "UTF-8");

		message.setContent(body, "text/html;charset=UTF-8");
		message.setSentDate(new Date());
		message.saveChanges();

		Transport.send(message, message.getAllRecipients());
		System.out.print("After sending the email");
	}catch(Exception ex ){
		System.out.print("Exception in sending the email"+ex);
		ex.printStackTrace();
		
	}
	}

	// To Validate any given email address
	public static boolean isvalideEmailSyntax(String email) {
		if (email == null || "".equals(email) || email.indexOf("@") == -1)
			return false;
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
