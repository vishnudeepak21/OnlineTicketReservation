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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ticketbooking.Service.EventListService;
import com.ticketbooking.Service.GetSeatCountService;
import com.ticketbooking.Service.UnblockSeatService;
import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;
import com.ticketbooking.util.MapEventBlockByEvent;
import com.ticketbooking.validator.UserSeatUnBlockEventValidator;

@Controller
public class UnBlockSeatController {

	@Autowired
	private UserSeatUnBlockEventValidator unBlockEventValidator;
	@Autowired
	private MapEventBlockByEvent mapEventBlockByEvent;
	@Value("${game.booking.status.Available}")
	private String bookingAvailable;
	@Value("${game.booking.status.SoftBlocked}")
	private String bookingSoftBlocked;
	@Autowired
	private UnblockSeatService unblockSeatService;
	@Autowired
	private GetSeatCountService GetSeatCountService;
	@Autowired
	private EventListService eventListService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(unBlockEventValidator);
	}

	@RequestMapping(value = "/userunblockEvent/{id}")
	public String edit(@PathVariable String id, Model m) {
		Event event = unblockSeatService.getEventById(id);
		EventSeatBlockDtl eventSeatBlockDtl = mapEventBlockByEvent.getMappedeventSeatBlock(event);
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateUserInfo(eventSeatBlockDtl);
		if (!(unblockSeatService.checkCustomerBookingStatus(eventSeatBlockDtl) > 0)) {
			m.addAttribute("eventList", eventListService.eventListByDate());
			m.addAttribute("command", "");
			m.addAttribute("errormessage", "No seat block found!!!");
			return "UserHome";
		}
		eventSeatBlockDtl
				.setTotalSeatBooked(String.valueOf(GetSeatCountService.getBlockedSeatCount(eventSeatBlockDtl)));
		eventSeatBlockDtl
				.setCancelledSeatCount(String.valueOf(GetSeatCountService.getUnBlockedSeatCount(eventSeatBlockDtl)));
		m.addAttribute("command", "");
		m.addAttribute("eventSeatBlockDtl", eventSeatBlockDtl);
		m.addAttribute("errorMessage", "");
		return "UnBlockSeat";
	}

	@RequestMapping(value = "/userUnblockSeat", method = RequestMethod.POST)
	public String UpdateSeInDb(@ModelAttribute("eventSeatBlockDtl") @Validated EventSeatBlockDtl eventSeatBlockDtl,
			BindingResult br, Model m) {
		if (br.hasErrors()) {
			m.addAttribute("command", eventSeatBlockDtl);
			m.addAttribute("Success", "");
			m.addAttribute("errorMessage", "");
			return "UnBlockSeat";
		}
		Event event = unblockSeatService.getEventById(eventSeatBlockDtl.getGameEventId());
		event = mapEventBlockByEvent.UpdateSeatCount(event, eventSeatBlockDtl, true);
		unblockSeatService.updateEventSeatCount(event);
		event.setBookingStatus(bookingSoftBlocked);
		if (event.getNumberOfSeats().equals("0")) {
			event.setBookingStatus(bookingAvailable);
		}
		unblockSeatService.updateEventBookingStatus(event);
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateCancelBookingDate(eventSeatBlockDtl);
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalBookingCount(eventSeatBlockDtl, true);
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalCancelledSeatCount(eventSeatBlockDtl, true);
		unblockSeatService.updateCancellationSeat(eventSeatBlockDtl);
		m.addAttribute("Success", "");
		m.addAttribute("command", "");
		m.addAttribute("errorMessage", "");
		EventSeatBlockDtl eventSeatBlockDtlUpd = mapEventBlockByEvent.getMappedeventSeatBlock(event);
		eventSeatBlockDtlUpd = mapEventBlockByEvent.UpdateUserInfo(eventSeatBlockDtlUpd);
		eventSeatBlockDtlUpd.setTotalSeatBooked(eventSeatBlockDtl.getTotalSeatBooked());
		eventSeatBlockDtlUpd.setCancelledSeatCount(eventSeatBlockDtl.getCancelledSeatCount());
		m.addAttribute("eventSeatBlockDtl", eventSeatBlockDtlUpd);
		return "UnBlockSeat";
	}
}
