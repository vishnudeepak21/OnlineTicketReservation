package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.EventSeatBlockDtl;

@Component
public class UserSeatUnBlockEventValidator implements Validator{
	
	@Value("${game.status.inactive}")
	private String statusInActive;

	@Value("${numberOfSeatsPattern}")
	private String numberOfSeatsRegex;
	
	public boolean supports(Class<?> clazz) {
		return EventSeatBlockDtl.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "actualCancelledSeatCount", "error.numberOfSeats.required");
		EventSeatBlockDtl eventSeatBlockDtl = (EventSeatBlockDtl) obj;
		if((eventSeatBlockDtl.getShowStatus().equals(statusInActive))) {
			err.rejectValue("gameEventId", "game.booking.status.inValid.unblockseat");
		}
		if(!eventSeatBlockDtl.getActualCancelledSeatCount().matches(numberOfSeatsRegex)) {
			err.rejectValue("numberOfSeats", "error.numberOfSeats.invalid");
		}
		try {
			if(Integer.parseInt(eventSeatBlockDtl.getTotalSeatBooked()) < Integer.parseInt(eventSeatBlockDtl.getActualCancelledSeatCount())) {
				err.rejectValue("gameEventId", "game.cancel.seat");
			}
		}catch(Exception e) {
			err.rejectValue("gameEventId", "technicalError");
		}
		
	}
	
}
