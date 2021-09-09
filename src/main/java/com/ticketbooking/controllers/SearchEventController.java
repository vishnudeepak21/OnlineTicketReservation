package com.ticketbooking.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketbooking.Service.EventListService;
import com.ticketbooking.beans.Event;

@Controller
public class SearchEventController {

	@Autowired
	private EventListService eventListService;

	@RequestMapping("/search")
	public String showSearchResults(@RequestParam("eventName") String eventName, Model m) {

		List<Event> event = eventListService.eventListByName(eventName);
		if (event == null) {
			m.addAttribute("eventList", new ArrayList<Event>());
			m.addAttribute("errormessage", "Entered Show Name is Invalid or Not Available");
		} else if(event.size()<=0){
			m.addAttribute("eventList", new ArrayList<Event>());
			m.addAttribute("errormessage", "Entered Show Name is Invalid or Not Available");
		}else {
			m.addAttribute("errormessage", "");
		}
		m.addAttribute("eventList", event);
		m.addAttribute("command", "");

		return "UserHome";

	}

}
