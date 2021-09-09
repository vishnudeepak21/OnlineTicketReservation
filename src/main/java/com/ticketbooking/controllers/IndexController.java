package com.ticketbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.beans.User;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showUserLoginForm(Model m) {
		m.addAttribute("command", "");
		m.addAttribute("user", new User());
		return "UserLogin";
	}

}
