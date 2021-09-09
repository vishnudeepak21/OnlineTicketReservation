package com.ticketbooking.beans;

import javax.validation.constraints.Pattern;


public class User {
	@Pattern(regexp="^[0-9]{9}",message="Registration Number should be of 9 digit number, Please try again")  
	private String registrationNumber = "";
	@Pattern(regexp="^[A-Za-z0-9\\s$&+,:;=?@#|'<>.^*()%!-]{8}",message="Entered Password is invalid") 
	private String password = "";
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
