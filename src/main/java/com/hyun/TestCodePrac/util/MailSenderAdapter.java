package com.hyun.TestCodePrac.util;

import org.springframework.stereotype.Component;

// 후에 Mail 클래스가 완성될 시 코드를 완성
@Component
public class MailSenderAdapter implements MailSender {

	private Mail mail;

	public MailSenderAdapter(){
		this.mail = new Mail();
	}

	@Override
	public boolean send() {
		return mail.sendMail();
	}

}
