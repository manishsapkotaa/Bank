package system.banking;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Scanner;

public class Sendmail {

	public static void main(String[] args) {
		System.out.println("Do You Want BankStatement in Mail?(true/false)\n");
		Scanner sc=new Scanner(System.in);
		boolean inp=sc.nextBoolean();
		if(inp==true) {
			

			final String username = "golesandip98@gmail.com"; // enter your mail id
			final String password = "***********";// enter ur password

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			  new		  javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("golesandip98@gmail.com")); // same email id
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("sgole988@gmail.com"));// whome u have to send mails that person id
					//message
					message.setSubject("Bank Statement");
					message.setText("Your new Bankstatement");
					//body attach
					MimeMultipart emailContent=new MimeMultipart();
					MimeBodyPart pdf=new MimeBodyPart();
					MimeBodyPart t=new MimeBodyPart();
					t.setText("My BankStatement");
					
					try {
						pdf.attachFile("C:/Users/sandip/Bankstatement3.pdf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					emailContent.addBodyPart(t);
					emailContent.addBodyPart(pdf);
				
					message.setContent(emailContent);
					Transport.send(message);

					System.out.println(" Message Send ");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
				}
		else
		{
		System.out.println("Bankstatement is not sent in mail");	
		}
			}
		}