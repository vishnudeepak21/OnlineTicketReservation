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

import com.ticketbooking.Service.GetSeatCountService;
import com.ticketbooking.Service.UnblockSeatService;
import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;
import com.ticketbooking.util.MapEventBlockByEvent;
import com.ticketbooking.validator.AdminSeatUnBlockEventValidator;

@Controller
public class AdminUnblockSeatController {

	@Autowired
	private AdminSeatUnBlockEventValidator adminSeatUnBlockEventValidator;
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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(adminSeatUnBlockEventValidator);
	}

	@RequestMapping(value = "/adminunblockEvent/{id}")
	public String adminUnblocSeat(@PathVariable String id, Model m) {
		Event event = unblockSeatService.getEventById(id);
		EventSeatBlockDtl eventSeatBlockDtl = mapEventBlockByEvent.getMappedeventSeatBlock(event);
		m.addAttribute("command", "");
		m.addAttribute("errorMessage", "");
		m.addAttribute("eventSeatBlockDtl", eventSeatBlockDtl);
		return "AdminUnBlockSeat";
	}

	@RequestMapping(value = "/unblockSeat", method = RequestMethod.POST)
	public String adminUnblockSeatInDb(
			@ModelAttribute("eventSeatBlockDtl") @Validated EventSeatBlockDtl eventSeatBlockDtl, BindingResult br,
			Model m) {
		if (br.hasErrors()) {
			m.addAttribute("command", eventSeatBlockDtl);
			m.addAttribute("Success", "");
			m.addAttribute("errorMessage", "");
			return "AdminUnBlockSeat";
		}
		int seatCount = unblockSeatService.checkCustomerBookingStatus(eventSeatBlockDtl);
		if ((seatCount <= 0)) {
			m.addAttribute("command", eventSeatBlockDtl);
			m.addAttribute("Success", "");
			m.addAttribute("errorMessage", "No Seat booked by the user for the event");
			return "AdminUnBlockSeat";
		} else if (seatCount < Integer.parseInt(eventSeatBlockDtl.getActualCancelledSeatCount())) {
			m.addAttribute("command", eventSeatBlockDtl);
			m.addAttribute("Success", "");
			String errorMessage = "User has booked only" + seatCount;
			m.addAttribute("errorMessage", errorMessage);
			return "AdminUnBlockSeat";
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
		eventSeatBlockDtl
				.setTotalSeatBooked((String.valueOf(GetSeatCountService.getBlockedSeatCount(eventSeatBlockDtl))));
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalBookingCount(eventSeatBlockDtl, true);
		eventSeatBlockDtl
				.setCancelledSeatCount(String.valueOf(GetSeatCountService.getUnBlockedSeatCount(eventSeatBlockDtl)));
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalCancelledSeatCount(eventSeatBlockDtl, true);
		unblockSeatService.updateCancellationSeat(eventSeatBlockDtl);
		m.addAttribute("Success", "User seat is released Successfully");
		m.addAttribute("command", "");
		EventSeatBlockDtl eventSeatBlockDtlUpd = mapEventBlockByEvent.getMappedeventSeatBlock(event);
		eventSeatBlockDtlUpd = mapEventBlockByEvent.UpdateUserInfo(eventSeatBlockDtlUpd);
		eventSeatBlockDtlUpd.setTotalSeatBooked(eventSeatBlockDtl.getTotalSeatBooked());
		eventSeatBlockDtlUpd.setCancelledSeatCount(eventSeatBlockDtl.getCancelledSeatCount());
		m.addAttribute("eventSeatBlockDtl", eventSeatBlockDtlUpd);
		m.addAttribute("errorMessage", "");
		return "AdminUnBlockSeat";
	}

}
