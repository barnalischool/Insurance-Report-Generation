package com.insurance.report.utility;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendEmail(File attachment) throws Exception{
		
		/*
		 * this code is for sending simple email without attachment
		 * 
		 * 
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("mail2barnali1990@gmail.com");
        message.setTo("developer.barnali@gmail.com"); 
        message.setSubject("Insurance Report"); 
        message.setText("");
        emailSender.send(message);
        */
		
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject("Email Subject");
		mimeMessageHelper.setText("Email Body", true);
		mimeMessageHelper.addAttachment("Insurance-Report", attachment);
		mimeMessageHelper.setFrom("mail2barnali1990@gmail.com");
		mimeMessageHelper.setTo("developer.barnali@gmail.com");
		
		emailSender.send(mimeMessage);
	}

}
