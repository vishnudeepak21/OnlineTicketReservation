package com.ticketbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.Service.AddEvent;
import com.ticketbooking.Service.GetMaxEventId;
import com.ticketbooking.beans.Event;
import com.ticketbooking.validator.AddEventValidator;

@Controller
public class AddEventController {

	@Autowired
	private GetMaxEventId maxEventId;
	@Autowired
	private AddEventValidator addBookValidator;
	@Autowired
	private AddEvent addEvent;

	@Value("${game.status.active}")
	private String statusActive;
	@Value("${game.status.inactive}")
	private String statusInActive;
	@Value("${game.booking.status.Available}")
	private String bookingAvailable;
	@Value("${game.booking.status.NotAvailable}")
	private String bookingNotAvailable;
	@Value("${game.booking.status.SoftBlocked}")
	private String bookingSoftBlocked;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(addBookValidator);
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public String addEvent(Model m) {
		Event event = new Event();
		event.setGameEventId(String.valueOf(Integer.parseInt(maxEventId.getMaxId()) + 1));
		m.addAttribute("command", "");
		m.addAttribute("event", event);
		return "AddEvent";
	}

	@RequestMapping(value = "/addEventInDb", method = RequestMethod.POST)
	public String addEventInDb(@ModelAttribute("event") @Validated Event event, BindingResult br, Model m) {
		if (br.hasErrors()) {
			m.addAttribute("command", event);
			m.addAttribute("Success", "");
			return "AddEvent";
		}
		event.setShowStatus(statusActive);
		event.setBookingStatus(bookingAvailable);
		addEvent.addEvent(event);
		m.addAttribute("Success", "Game Event is inserted Successfully in the system");
		m.addAttribute("command", "");
		event = new Event();
		event.setGameEventId(String.valueOf(Integer.parseInt(maxEventId.getMaxId()) + 1));
		m.addAttribute("event", event);
		return "AddEvent";
	}

}
