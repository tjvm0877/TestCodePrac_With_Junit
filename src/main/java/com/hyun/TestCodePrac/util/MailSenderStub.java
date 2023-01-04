package com.hyun.TestCodePrac.util;

import org.springframework.stereotype.Component;

// 아직 구현되지않은 클래스
@Component
public class MailSenderStub implements MailSender {

	@Override
	public boolean send() {
		return true;
	}
}
