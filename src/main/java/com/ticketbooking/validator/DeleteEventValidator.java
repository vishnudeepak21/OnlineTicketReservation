package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.Event;

@Component
public class DeleteEventValidator implements Validator{
	
	@Value("${game.status.inactive}")
	private String statusInActive;
	@Value("${numberOfSeatsPattern}")
	private String numberOfSeatsRegex;
	@Value("${datePattern}")
	private String datePattern;

	public boolean supports(Class<?> clazz) {
		return Event.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {
		
		Event event = (Event) obj;

		if((event.getShowStatus().equals(statusInActive))) {
			err.rejectValue("gameEventId", "game.booking.status.Invalid.delete");
		}
		
	}
}
