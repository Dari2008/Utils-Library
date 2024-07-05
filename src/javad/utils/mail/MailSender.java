package javad.utils.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {
	
	private String message;
	private String subject;
	public String ToMail;
	public String FromMail;
	
	public MailSender(String message, String subject, String FromMail, String ToMail) {
	this.message = message;	
	this.subject = subject;
	this.FromMail = FromMail;
	this.ToMail = ToMail;
	}
	
	public void setSubject(String subject) {
		
	}
	
	public void setMessage(String text) {
		
	}
	
	public void setToMail(String to){
		this.ToMail = to;
	}
	
	public void setFromMail(String from){
		this.FromMail = from;
	}
	
	
		
		protected Session mailSession;

		public void login(String smtpHost, String smtpPort, String username, String password) {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.socketFactory.port", smtpPort);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", smtpPort);

			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			this.mailSession = Session.getDefaultInstance(props, auth);
		}

		public void send()
				throws MessagingException, IllegalStateException, UnsupportedEncodingException {
			if (mailSession == null) {
				throw new IllegalStateException("Du musst dich zuerst einloggen (login()-Methode)");
			}

			MimeMessage msg = new MimeMessage(mailSession);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(FromMail, "<" + FromMail + ">"));
			msg.setReplyTo(InternetAddress.parse(FromMail, false));
			msg.setSubject(subject, "UTF-8");
			msg.setText(message, "UTF-8");
			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToMail, false));

			Transport.send(msg);
		
	}
	
}
