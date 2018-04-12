package com.service.setting.email;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSending {

	public EmailSending() {
		String host = "smtp.gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
//		props.put("mail.debug", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailSetting.username, EmailSetting.password);
			}
		});

		try {
			for (int i = 0; i < EmailSetting.administratorEmail.size(); i++) {
				
			
			Transport bus = session.getTransport("smtp");
			bus.connect();
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(EmailSetting.from));
			InternetAddress[] address = { new InternetAddress(EmailSetting.administratorEmail.get(i)) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(HTMLDataSource.deviceNumber);
			msg.setSentDate(new Date());

			setHTMLContent(msg);
			msg.saveChanges();
			bus.sendMessage(msg, address);

			bus.close();
			}

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private static void setHTMLContent(Message msg) throws MessagingException {
		msg.setDataHandler(new DataHandler(new HTMLDataSource(HTMLDataSource.getHTMLContent())));
	}

}
