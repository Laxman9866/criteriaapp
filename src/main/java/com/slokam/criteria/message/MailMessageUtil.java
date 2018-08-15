package com.slokam.criteria.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slokam.criteria.exception.ApplicationMessageException;
@Component
public class MailMessageUtil {
	
	@Autowired
	private MailMessageSender mailMessageSender;

	public IMessageSender sendMessageType(String type) throws ApplicationMessageException{
		IMessageSender messageSender=null;
		switch(type){
		case "mail": messageSender=mailMessageSender;
		break;
		default : throw new ApplicationMessageException("Invailid sender Type.");
		}
		return messageSender;
	}
}
