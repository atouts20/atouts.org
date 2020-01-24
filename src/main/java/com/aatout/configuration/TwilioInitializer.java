package com.aatout.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilioInitializer {
	private final static String account_sid = "AC2e31ef48be59ea8eadd5ef454b9db5d9";
	   private final static String 	auth_token = "abc136fde0375ad0e746579408cb3922";
	private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
	private final TwilioConfiguration twilioConfiguration;
	public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
		super();
		this.twilioConfiguration = twilioConfiguration;
		/*Twilio.init(
				twilioConfiguration.getAccount_sid(),
				twilioConfiguration.getAuth_token()
				account_sid: AC2e31ef48be59ea8eadd5ef454b9db5d9
				auth_token: abc136fde0375ad0e746579408cb3922
				);*/
		LOGGER.info("Twilio initializer ....... ######################### .....  whith account sid {}",twilioConfiguration.getAccount_sid());
	}
	static {
	      Twilio.init(account_sid, auth_token);
	   }

	

}
