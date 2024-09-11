/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ngoba
 */
public class MailUtils {
    public static String sendMail(String content, String to, String subject) {
		String status = "";
		final String from = "nbdung0000@gmail.com";
		final String pass = "snmjwgnhzfganuxe";
		Properties props = setUpSmtp();

		// create Authenticator
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pass);
			}
		};
		// phien lam viec
		Session session = Session.getInstance(props, auth);
		// gui email
		MimeMessage msg = new MimeMessage(session);
		try {
			// type of content
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			// set nguoi gui
			msg.setFrom(from);
			// set up nguoi nhan
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			// header
			msg.setSubject(subject);
			// set reply email	
			// set content
			msg.setContent(content, "text/html; charset=utf-8");
			//send mail
			Transport.send(msg);

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return status;
	}

	private static Properties setUpSmtp() {
		// khai bao thuoc tinh
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // host phuong phuc smtp
		props.put("mail.smtp.port", "587"); // TLS 587 SSL 486
		props.put("mail.smtp.auth", "true"); // can phai dang nhap
		props.put("mail.smtp.starttls.enable", "true");
		return props;
	}
}
