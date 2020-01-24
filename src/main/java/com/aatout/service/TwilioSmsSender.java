package com.aatout.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aatout.configuration.TwilioConfiguration;
import com.aatout.message.EnvoiSms;
import com.aatout.message.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
@Service("twilio")
public class TwilioSmsSender implements EnvoiSms {
	private final static Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
	private final TwilioConfiguration twilioConfiguration;
	@Autowired
	public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
		super();
		this.twilioConfiguration = twilioConfiguration;
	}

	@Override
	public void sendSms(SmsRequest smsRequest) {
		if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
			PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
			PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrial_number());
			String message = smsRequest.getMessage();
			MessageCreator creator = Message.creator(to, from, message);
			creator.create();
			LOGGER.info("Envoi de message {}" + smsRequest);
			
		}else {
			throw new IllegalArgumentException(
					"Le numero [" + smsRequest.getPhoneNumber() + "] est non valide");
		}
		
		
	}

	private boolean isPhoneNumberValid(String phoneNumber) {
		// TODO Auto-generated method stub
		return true;
	}

}
