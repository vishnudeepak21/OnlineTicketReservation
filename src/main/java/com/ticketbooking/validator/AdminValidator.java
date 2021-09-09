package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.Admin;

@Component
public class AdminValidator implements Validator{
	
	@Value("${adminMailId}")
	private String userId;
	@Value("${adminPassword}")
	private String userPassword;
	

	public boolean supports(Class<?> clazz) {
		return Admin.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {
		
		
		Admin admin = (Admin) obj;
		if(!(admin.getUserName().equals(userId) && admin.getPassword().equals(userPassword))) {
			err.rejectValue("password", "error.invalidlogin");
		}
		
	}
	
}
