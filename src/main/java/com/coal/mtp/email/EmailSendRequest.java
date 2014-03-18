package com.coal.mtp.email;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * email发送请求对象
 * 
 */
public class EmailSendRequest implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -4898993855934167700L;

	/**
	 * 邮件中发件人显示名
	 */
	private String senderNickName;

	/**
	 * 接收邮件的地址.
	 */
	private String recvAddress;

	/**
	 * 抄送邮件的地址.
	 */
	private String ccAddress;

	/**
	 * 发送邮件的地址.
	 */
	private String fromAddress;

	/**
	 * 密送邮件的地址.
	 */
	private String bccAddress;

	/**
	 * 主题.
	 */
	private String subject;

	/**
	 * 正文.
	 */
	private String body;

	/**
	 * 附件.
	 */
	private File[] attachmentFiles;

	public String getSenderNickName() {
		return senderNickName;
	}

	public void setSenderNickName(String senderNickName) {
		this.senderNickName = senderNickName;
	}

	public String getRecvAddress() {
		return recvAddress;
	}

	public void setRecvAddress(String recvAddress) {
		this.recvAddress = recvAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Send an email from ").append(getSenderNickName())
				.append("[").append(getFromAddress()).append("]")
				.append(" to ").append(getRecvAddress()).append(" cc ")
				.append(getCcAddress()).append(" bcc ").append(getBccAddress())
				.append(" at ").append(new Date()).append(". Mail content is:")
				.append(getBody());
		return buf.toString();
	}

	public File[] getAttachmentFiles() {
		return attachmentFiles;
	}

	public void setAttachmentFiles(File[] attachmentFiles) {
		this.attachmentFiles = attachmentFiles;
	}
}
