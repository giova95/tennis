package javaMail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class javaMailUtil {
	
	
	public static void sendMail(String recipient, String text, String subject) throws MessagingException{
		System.out.println("Preparing to send email");
		Properties prop = new Properties();
	
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
	
		String myEmail = "circolouniversitariotennis@gmail.com";
		String password ="ljwqjsxspluxvplq";
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(myEmail, password);
		    }
		});
		
		Message message = prepareMessage(session, myEmail, recipient, text, subject);
		
		Transport.send(message);
		System.out.println("email sent successfully");
	}

	private static Message prepareMessage(Session s, String myEmail, String recipient, String text, String subject){
			try {
				Message message = new MimeMessage(s);
				message.setFrom(new InternetAddress(myEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				message.setSubject(subject);
				message.setText(text);
				return message;
			}catch(MessagingException e) {
				e.printStackTrace();
				return null;
			}
	}		
}
