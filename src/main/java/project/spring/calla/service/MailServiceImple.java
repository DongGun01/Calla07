package project.spring.calla.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImple implements MailService{

	@Autowired   //context-mail���� �� ����߱� ������ ���Թ��� �� �ִ�. Spring���� �����ϴ� MailSender. 
    private JavaMailSenderImpl mailSender;
	
	
	public String sendMail(String mail) throws MessagingException {
		String emailCheckCode = String.valueOf((int)(Math.random() * (999999)));
		MimeMessage mailMessage = mailSender.createMimeMessage();
		String mailContent = "������ȣ : " + emailCheckCode; // ���� �̸��� ����
		mailMessage.setSubject("Goott mall ȸ������ �̸��� ����", "UTF-8"); // ���� ����
		mailMessage.setText(mailContent, "UTF-8");
		mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));  
		mailSender.send(mailMessage);
		return emailCheckCode;
	}
}
