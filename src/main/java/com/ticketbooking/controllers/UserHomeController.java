package com.ticketbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticketbooking.Service.EventListService;

@Controller
public class UserHomeController {

	@Autowired
	private EventListService eventListService;
	@Value("${userRegistrationNumber}")
	private String userId;

	@RequestMapping("/userhome")
	public String showUserHome(Model m) {
		m.addAttribute("eventList", eventListService.eventListByDate());
		m.addAttribute("command", "");
		m.addAttribute("errormessage", "");
		return "UserHome";
	}

	@RequestMapping("/softBlock")
	public String showUserSoftBlockInfo(Model m) {
		m.addAttribute("eventList", eventListService.eventSoftBlockDtl(userId));
		m.addAttribute("command", "");
		m.addAttribute("errormessage", "");
		return "UserSoftBlockInfo";
	}

}
