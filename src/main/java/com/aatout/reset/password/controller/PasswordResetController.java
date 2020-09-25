package com.aatout.reset.password.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aatout.dao.UserRepository;
import com.aatout.model.AppUser;
import com.aatout.reset.password.PasswordResetDto;
import com.aatout.reset.password.PasswordResetToken;
import com.aatout.reset.password.reposotory.PasswordResetTokenRepository;

import javax.validation.Valid;

@RestController 
@PreAuthorize("permitAll")
public class PasswordResetController {
	@Autowired
	private UserRepository userService;
    @Autowired 
    private PasswordResetTokenRepository tokenRepository;
    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    /*@GetMapping
    public PasswordResetDto displayResetPasswordPage(@RequestBody PasswordResetDto form) {

        PasswordResetToken resetToken = tokenRepository.findByToken(form.getToken());
        if (resetToken == null){
            System.out.println("Could not find password reset token.");
        } else if (resetToken.isExpired()){
        	System.out.println("Token has expired, please request a new password reset.");
        } else {
        	System.out.println("token" +resetToken.getToken());
        }

        return form;
    }
*/
    @PostMapping("/reset-password")
    @Transactional
    public PasswordResetDto handlePasswordReset( @Valid @RequestBody PasswordResetDto form) {
    	System.out.println(form);
    	
    	if(form.getToken() != null) {
    		PasswordResetToken token = tokenRepository.findByToken(form.getToken());
    		AppUser user = token.getUser();
	        String updatedPassword = passwordEncoder.encode(form.getPassword());
	        System.out.println("//////////////////");
	        userService.updatePassword(updatedPassword, user.getId());
	        System.out.println("//////////////////");
	        tokenRepository.delete(token);
    		if(token.isExpired()) {
    			return null;
    		} else {
    			/*AppUser user = token.getUser();
    	        String updatedPassword = passwordEncoder.encode(form.getPassword());
    	        System.out.println("//////////////////");
    	        userService.updatePassword(updatedPassword, user.getId());
    	        System.out.println("//////////////////");
    	        tokenRepository.delete(token);*/
    		}
    	}
		return form; 
    } 
}
