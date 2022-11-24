package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String mail, String password) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		
		simpleMessage.setTo("isabella23@naver.com");
		simpleMessage.setSubject("비밀번호 안내");
		simpleMessage.setText(password);
		
		javaMailSender.send(simpleMessage);
	}
}
