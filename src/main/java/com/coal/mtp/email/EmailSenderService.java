package com.coal.mtp.email;


/**
 * 
 * email通知,收件人字段为String类型，多个收件人之间用","分割
 */
public interface EmailSenderService {

	void send(final EmailSendRequest request);
}
