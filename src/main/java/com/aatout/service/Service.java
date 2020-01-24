package com.aatout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.aatout.message.EnvoiSms;
import com.aatout.message.SmsRequest;

@org.springframework.stereotype.Service
public class Service {
	private final EnvoiSms envoiSms;
	@Autowired
	public Service(@Qualifier("twilio") TwilioSmsSender envoiSms) {
		super();
		this.envoiSms = envoiSms;
	}
	
	public void sendSms(SmsRequest  smsRequest) {
		envoiSms.sendSms(smsRequest);
	}

}
