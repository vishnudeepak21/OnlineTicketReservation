package com.ticketbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.Service.DeleteEventService;
import com.ticketbooking.beans.Event;
import com.ticketbooking.validator.DeleteEventValidator;

@Controller
public class DeleteEventController {

	@Autowired
	private DeleteEventService deleteEventService;

	@Autowired
	private DeleteEventValidator deleteEventValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(deleteEventValidator);
	}

	@RequestMapping(value = "/deleteEvent/{id}")
	public String edit(@PathVariable String id, Model m) {
		Event event = deleteEventService.getEventById(id);
		m.addAttribute("command", "");
		m.addAttribute("event", event);
		return "DeleteEvent";
	}

	@RequestMapping(value = "/deleteEventInDb", method = RequestMethod.POST)
	public String UpdateEventInDb(@ModelAttribute("event") @Validated Event event, BindingResult br, Model m) {
		if (br.hasErrors()) {
			m.addAttribute("command", event);
			m.addAttribute("Success", "");
			return "DeleteEvent";
		}
		deleteEventService.deleteEventById(event);
		m.addAttribute("Success", "Game Event is inserted Successfully in the system");
		m.addAttribute("command", "");
		m.addAttribute("event", event);
		return "DeleteEvent";
	}

}
