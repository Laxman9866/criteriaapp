package com.slokam.criteria.thread;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailThread extends Thread{

	private JavaMailSender mailsender;
	private String folderZipPath;
	public MailThread(JavaMailSender mailsender,String folderZipPath){
		this.folderZipPath=folderZipPath;
		this.mailsender=mailsender;
	}
	@Override
	public void run() {
		try {
			MimeMessage mimeMessage=mailsender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper =new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setTo("laxman.lucky005@gmail.com");
			mimeMessageHelper.setFrom("laxman.lucky005@gmail.com");
			mimeMessageHelper.setText("Find Attachment");
			mimeMessageHelper.setSubject("Attachment File");
			FileSystemResource fsr=new FileSystemResource(folderZipPath);
			mimeMessageHelper.addAttachment("Zipfloder.zip", fsr);
			mailsender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
