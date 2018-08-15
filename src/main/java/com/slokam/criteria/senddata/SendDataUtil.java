package com.slokam.criteria.senddata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendDataUtil {

	@Autowired
	private FileGeneratorResponsibility fileGeneratorResponsibility;
	@Autowired
	private ZipFileResponsibility zipFileResponsibility;
	@Autowired
	private MessageSendResponsibility messageSendResponsibility;
	public AbSendDataResponsibility sendResps(){
		this.fileGeneratorResponsibility.setAbSendDataResponsibility(zipFileResponsibility);
		this.zipFileResponsibility.setAbSendDataResponsibility(messageSendResponsibility);
		return fileGeneratorResponsibility;
	}
}
