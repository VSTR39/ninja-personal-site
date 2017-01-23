package controllers;

import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.PasswordRestoreDao;
import models.PasswordRestoreInformation;
import ninja.Context;
import ninja.Result;
import ninja.Results;

public class PasswordRestoreController {
	@Inject
	PasswordRestoreDao passwordRestore;

	public Result restorePassword(Context context, PasswordRestoreInformation email) {
		boolean isRegisteredUser = passwordRestore.isRegisteredUser(email.getEmail());
		if (isRegisteredUser) {
			String to = email.getEmail();
			String from = "vstrifonov93@gmail.com";
			String fromPassword = "121212076vstr";
			String host = "smtp.gmail.com";
			
			Properties properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.user", from);
			properties.put("mail.smtp.password", fromPassword);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.host", host);
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);

			try {
				Transport transport = session.getTransport("smtp");
			    transport.connect(host, from, fromPassword);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Password Restore");
				message.setText(passwordRestore.getRegisteredUserCredentials(email.getEmail()) + "\nYour welcome");
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			context.getFlashScope().success("Password sent.");
			return Results.redirect("/");
		}
		context.getFlashScope().error("Invalid Email.");
		return Results.redirect("/");
	}
}
