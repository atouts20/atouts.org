package com.aatout.random;

import java.util.Random;

import org.springframework.stereotype.Service;
@Service
public class RandomCodeImplement implements RandomCodeService {

	@Override
	public Long pin() {
		Random rdm = new Random();
		long pin = rdm.nextLong();
		return pin;
	}
	 

}
