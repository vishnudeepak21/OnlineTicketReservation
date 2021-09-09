package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.EventSeatBlockDtl;

@Component
public class AdminSeatUnBlockEventValidator implements Validator{
	
	@Value("${game.status.inactive}")
	private String statusInActive;
	
	@Value("${numberOfSeatsPattern}")
	private String numberOfSeatsRegex;
	
	@Value("${userRegistrationNumber}")
	private String userId;
	
	public boolean supports(Class<?> clazz) {
		return EventSeatBlockDtl.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {

		ValidationUtils.rejectIfEmptyOrWhitespace(err, "actualCancelledSeatCount", "error.numberOfSeats.required");
		EventSeatBlockDtl eventSeatBlockDtl = (EventSeatBlockDtl) obj;
		
		if(!eventSeatBlockDtl.getActualCancelledSeatCount().matches(numberOfSeatsRegex)) {
			err.rejectValue("numberOfSeats", "error.numberOfSeats.invalid");
		}
		
		if((eventSeatBlockDtl.getShowStatus().equals(statusInActive))) {
			err.rejectValue("gameEventId", "game.booking.status.Invalid.delete");
		}
		if(!eventSeatBlockDtl.getUserId().equals((eventSeatBlockDtl.getUserId()))) {
			err.rejectValue("gameEventId", "error.seat.checkUserCred");
		}	
		
	}
	
}
