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

import com.ticketbooking.Service.EventListService;
import com.ticketbooking.beans.User;
import com.ticketbooking.validator.UserValidator;

@Controller
public class UserLoginController {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private EventListService eventListService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	@RequestMapping("/userLogin")
	public String showUserLoginForm(Model m) {
		m.addAttribute("command", "");
		m.addAttribute("user", new User());
		return "UserLogin";
	}

	@RequestMapping("/userLoginCheck")
	public String checkUserCred(@ModelAttribute("user") @Validated User user, BindingResult br, Model m) {

		if (br.hasErrors()) {
			m.addAttribute("command", user);
			return "UserLogin";
		}
		m.addAttribute("eventList", eventListService.eventListByDate());
		m.addAttribute("command", "");
		m.addAttribute("errormessage", "");
		return "UserHome";
	}

}
