package com.ticketbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticketbooking.Service.EventListService;

@Controller
public class AdminHomeController {

	@Autowired
	private EventListService eventListService;

	@RequestMapping("/previous")
	public String showUserLoginForm(Model m) {
		m.addAttribute("eventList", eventListService.eventList());
		m.addAttribute("command", "");
		return "AdminHome";
	}

}
