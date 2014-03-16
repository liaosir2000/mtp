package com.coal.mtp.email;

import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 
 * email发送通知,收件人字段为String类型，多个收件人之间用","分割
 * 
 */
@Service
public class EmailSenderServiceImpl extends JavaMailSenderImpl implements
		EmailSenderService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static final String DEFAULT_ENCODING = "utf-8";
	@Autowired
	private JavaMailSender mailSender;
	@Value("${mail.from}")
	private String from;
	@Value("${mail.displayName}")
	private String displayName;

	public void send(EmailSendRequest request) {

		// send eMail
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true,
					DEFAULT_ENCODING);

			// 收件人地址
			String recvAddress = request.getRecvAddress();
			if (null == recvAddress || recvAddress.length() <= 0) {
				return;
			}
			helper.setTo(InternetAddress.parse(recvAddress));
			// 抄送人地址
			String ccAddress = request.getCcAddress();
			if (null != ccAddress && ccAddress.length() > 0) {
				helper.setCc(InternetAddress.parse(ccAddress));
			}
			// 密送人地址
			String bccAddress = request.getBccAddress();
			if (null != bccAddress && bccAddress.length() > 0) {
				helper.setBcc(InternetAddress.parse(bccAddress));
			}

			// 邮件附件 (多份附件用","隔开)
			if (null != request.getAttachmentFiles()
					&& request.getAttachmentFiles().length > 0) {
				for (File file : request.getAttachmentFiles()) {
					if (file != null && file.exists()) {
						String fileName = file.getName();
						helper.addAttachment(fileName, file);
					}
				}
			}

			helper.setFrom(from, displayName);
			helper.setSubject(request.getSubject());
			helper.setText(request.getBody(), true);
			mailSender.send(msg);

		} catch (Exception e) {
			logger.error("email service error:", e);
		}

	}

	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
