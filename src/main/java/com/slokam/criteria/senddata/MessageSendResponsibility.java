package com.slokam.criteria.senddata;

import javax.activation.MailcapCommandMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slokam.criteria.exception.ApplicationMessageException;
import com.slokam.criteria.message.IMessageSender;
import com.slokam.criteria.message.MailMessageUtil;
@Component
public class MessageSendResponsibility extends AbSendDataResponsibility{

	@Autowired
	private MailMessageUtil mailMessageUtil;
	@Override
	public void prepairResponsibilities(SendDataResponsibilityPojo pojo) {
		IMessageSender messageSender;
		try {
			
			messageSender = mailMessageUtil.sendMessageType(pojo.getPersonSearch().getMailType());
			messageSender.sendMessages(pojo.getZipFilePath());
		} catch (ApplicationMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
