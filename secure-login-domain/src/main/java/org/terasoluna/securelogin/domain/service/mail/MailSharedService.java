package org.terasoluna.securelogin.domain.service.mail;

import java.util.List;

import org.terasoluna.securelogin.domain.model.ReceivedMail;

public interface MailSharedService {
	void send(String to, String text);
	
	List<ReceivedMail> getReceivedMessages();
}
