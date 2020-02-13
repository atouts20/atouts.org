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

	@Override
	public String pinString(int length) {
		 String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
		    String pass = "";
		    for(int x=0;x<length;x++)
		    {
		       int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
		       pass += chars.charAt(i);
		    }
		    System.out.println(pass);
		    return pass;
	}
	 

}
