package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.EventSeatBlockDtl;

@Component
public class UserSeatBlockEventValidator implements Validator{
	
	@Value("${game.status.inactive}")
	private String statusInActive;
	
	@Value("${numberOfSeatsPattern}")
	private String numberOfSeatsRegex;
	
	public boolean supports(Class<?> clazz) {
		return EventSeatBlockDtl.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "actualSeatBooked", "error.numberOfSeats.required");
		
		EventSeatBlockDtl eventSeatBlockDtl = (EventSeatBlockDtl) obj;
		if((eventSeatBlockDtl.getShowStatus().equals(statusInActive))) {
			err.rejectValue("gameEventId", "game.booking.status.inValid.seatBook");
		}
		if(!eventSeatBlockDtl.getActualSeatBooked().matches(numberOfSeatsRegex)) {
			err.rejectValue("numberOfSeats", "error.numberOfSeats.invalid");
		}
		try {
			if(Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked())>Integer.parseInt(eventSeatBlockDtl.getNumberOfSeats())) {
				err.rejectValue("gameEventId", "game.booking.seat.maxSeatBooked");
			}
			if(Integer.parseInt(eventSeatBlockDtl.getTotalSeatBooked()) + Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked())>5) {
				err.rejectValue("gameEventId", "game.booking.seat.maxBooked");
			}
		}catch(Exception e) {
			err.rejectValue("gameEventId", "technicalError");
		}
		
	}
	
}
