package com.slokam.criteria.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.slokam.criteria.exception.ApplicationMessageException;
import com.slokam.criteria.thread.MailThread;
@Component
public class MailMessageSender implements IMessageSender{

	@Autowired
	private JavaMailSender mailSender;
	@Override
	public void sendMessages(String zipFilePath) throws ApplicationMessageException {
		MailThread thread=new MailThread(mailSender, zipFilePath);
		thread.start();
	}

}
