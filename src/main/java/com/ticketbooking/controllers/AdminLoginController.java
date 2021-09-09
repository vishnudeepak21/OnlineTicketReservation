package com.ticketbooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.Service.EventListService;
import com.ticketbooking.beans.Admin;
import com.ticketbooking.validator.AdminValidator;

@Controller
public class AdminLoginController {

	@Autowired
	private AdminValidator adminValidator;

	@Autowired
	private EventListService eventListService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(adminValidator);
	}

	@RequestMapping("/adminLogin")
	public String showAdminLoginForm(Model m) {
		m.addAttribute("command", "");
		m.addAttribute("admin", new Admin());
		return "AdminLogin";
	}

	@RequestMapping(value = "/adminLoginCheck", method = RequestMethod.POST)
	public String checkAdminCred(@ModelAttribute("admin") @Validated Admin admin, BindingResult br, Model m) {

		if (br.hasErrors()) {
			m.addAttribute("command", admin);
			return "AdminLogin";
		}
		m.addAttribute("eventList", eventListService.eventList());
		m.addAttribute("command", "");
		return "AdminHome";
	}

}
