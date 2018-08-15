package com.slokam.criteria.message;

import com.slokam.criteria.exception.ApplicationMessageException;

public interface IMessageSender {

	public void sendMessages(String zipFilePath) throws ApplicationMessageException;
}
