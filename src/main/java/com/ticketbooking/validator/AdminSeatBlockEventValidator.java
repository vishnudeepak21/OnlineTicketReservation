package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.EventSeatBlockDtl;

@Component
public class AdminSeatBlockEventValidator implements Validator{
	
	@Value("${numberOfSeatsPattern}")
	private String numberOfSeatsRegex;
	
	@Value("${game.status.inactive}")
	private String statusInActive;
	
	@Value("${userRegistrationNumber}")
	private String userId;
	
	public boolean supports(Class<?> clazz) {
		return EventSeatBlockDtl.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "actualSeatBooked", "error.numberOfSeats.required");
		
		EventSeatBlockDtl eventSeatBlockDtl = (EventSeatBlockDtl) obj;
		
		if(!eventSeatBlockDtl.getActualSeatBooked().matches(numberOfSeatsRegex)) {
			err.rejectValue("numberOfSeats", "error.numberOfSeats.invalid");
		}
		
		if((eventSeatBlockDtl.getShowStatus().equals(statusInActive))) {
			err.rejectValue("gameEventId", "game.booking.status.inValid.seatBook");
		}
		if(!eventSeatBlockDtl.getUserId().equals((eventSeatBlockDtl.getUserId()))) {
			err.rejectValue("gameEventId", "error.seat.checkUserCred");
		}	
		try {
			if(Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked())>Integer.parseInt(eventSeatBlockDtl.getNumberOfSeats())) {
				err.rejectValue("gameEventId", "game.booking.seat.maxSeatBooked");
			}
		}catch(Exception e) {
			err.rejectValue("gameEventId", "technicalError");
		}
		
	}
	
}
