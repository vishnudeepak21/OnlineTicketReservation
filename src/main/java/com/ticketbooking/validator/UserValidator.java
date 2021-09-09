package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.User;

@Component
public class UserValidator implements Validator{
	
	@Value("${userRegistrationNumber}")
	private String userId;
	@Value("${userPassword}")
	private String userPassword;
	@Value("${RegistrationNumberPattern}")
	private String registrationNumberRegex;
	@Value("${passwordPattern}")
	private String passwordRegex;
	

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {
		
		User user = (User) obj;
	
		if(!user.getRegistrationNumber().matches(registrationNumberRegex)) {	
			err.rejectValue("password", "error.userLogin.registrationNumber");
		}
		
		if(!user.getPassword().matches(passwordRegex)) {
			err.rejectValue("password", "error.userLogin.password");
		}
		
		if(!(user.getRegistrationNumber().equals(userId) && user.getPassword().equals(userPassword)) 
				&& user.getRegistrationNumber().matches(registrationNumberRegex) && user.getPassword().matches(passwordRegex)) {
			err.rejectValue("password", "error.invalidlogin");
		}
		
	}
	
}
