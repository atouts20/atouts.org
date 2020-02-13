package com.aatout.reset.password.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.RestController;

import com.aatout.dao.UserRepository;
import com.aatout.model.AppUser;
import com.aatout.reset.password.PasswordForgotDto;
import com.aatout.reset.password.PasswordResetToken;
import com.aatout.reset.password.reposotory.PasswordResetTokenRepository;
import com.aatout.service.EmailService;

@RestController
@RequestMapping("/forgot-password")
@PreAuthorize("permitAll")
public class PasswordForgotController {
	@Autowired
	private UserRepository userService;

	@Autowired
	private EmailService emailService;

	@Autowired 
	private PasswordResetTokenRepository tokenRepository;


	/*@ModelAttribute("forgotPasswordForm")
	public PasswordForgotDto forgotPasswordDto() {
		return new PasswordForgotDto();
	}*/

	@GetMapping
	public String displayForgotPasswordPage() {
		return "forgot-password";
	}

	@PostMapping
	public String processForgotPasswordForm(@RequestBody @Valid PasswordForgotDto form,
			BindingResult result,
			HttpServletRequest request) {
		System.out.println(" TEST TEST TEST");
		System.out.println(form);

		if (result.hasErrors()){
			return "forgot-password";
		}
		
		System.out.println(form.getEmail());
		
		AppUser user = userService.findBySupprimeIsFalseAndEnabledIsTrueAndEmailIs(form.getEmail());
		System.out.println(user);
		System.out.println(" TEST TEST TEST");
		System.out.println(form);
		if (user == null){
			result.rejectValue("email", null, "We could not find an account for that e-mail address.");
			return "forgot-password";
		}

		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(30);
		tokenRepository.save(token);

		/*SimpleMailMessage forget = new SimpleMailMessage();
		//Mail mail = new Mail();
		forget.setTo(user.getEmail());
		forget.setSubject("Password reset request");
		forget.setFrom("aamadoudiko@gmail.com");*/

		

		/*Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("user", user);
		model.put("signature", "https://memorynotfound.com");*/

		/*String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		forget.setText("Pour récuperé votre compte atouts, veuillez cliquer sur le lien aatout ci-dessous:\n"
				+ url + "/reset-password?token=" + token.getToken());
		//model.put("resetUrl", url + "/reset-password?token=" + token.getToken());

		emailService.sendEmail(forget);*/
			String url = "https://atouts20.github.io/atouts";
		//String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String nom = user.getPrenom() +" "+user.getNom();
		String msg = "Pour recupere votre compte atouts, veuillez cliquer sur le lien aatout ci-dessous:\n"
				+ url + "/reset-password?token=" + token.getToken();
		emailService.sendMailHtml(user.getEmail(), "RECUPERATION MOT DE PASSE", msg, nom);
		

		return "redirect:/forgot-password?success";

	}

}
