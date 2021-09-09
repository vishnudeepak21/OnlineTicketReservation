package com.ticketbooking.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ticketbooking.beans.Event;

@Component
public class EditEventValidator implements Validator{
	
	@Value("${game.status.inactive}")
	private String statusInActive;
	
	@Value("${numberOfSeatsPattern}")
	private String numberOfSeatsRegex;
	@Value("${inputDatePattern}")
	private String datePattern;
	
	public boolean supports(Class<?> clazz) {
		return Event.class.equals(clazz);
	}
	
	public void validate(Object obj, Errors err) {
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "gameEventName", "error.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "showDate", "error.date.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "numberOfSeats", "error.numberOfSeats.required");
		
		Event event = (Event) obj;
		if((event.getShowStatus().equals(statusInActive))) {
			err.rejectValue("gameEventId", "game.booking.status.Invalid.edit");
		}
		
		if(!event.getNumberOfSeats().matches(numberOfSeatsRegex)) {
			err.rejectValue("numberOfSeats", "error.numberOfSeats.invalid");
		}
		
		if(!event.getShowDate().matches(datePattern)) {
			err.rejectValue("showDate", "error.eventDate.invalid");
		}
		
	}
}
