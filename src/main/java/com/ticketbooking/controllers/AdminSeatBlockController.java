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

import com.ticketbooking.Service.BlockSeatService;
import com.ticketbooking.Service.GetSeatCountService;
import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;
import com.ticketbooking.util.MapEventBlockByEvent;
import com.ticketbooking.validator.AdminSeatBlockEventValidator;

@Controller
public class AdminSeatBlockController {

	@Autowired
	private BlockSeatService blockSeatService;
	@Autowired
	private MapEventBlockByEvent mapEventBlockByEvent;
	@Value("${game.booking.status.SoftBlocked}")
	private String bookingSoftBlocked;
	@Value("${game.booking.status.NotAvailable}")
	private String bookingNotAvailable;
	@Autowired
	private AdminSeatBlockEventValidator adminSeatBlockEventValidator;
	@Autowired
	private GetSeatCountService GetSeatCountService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(adminSeatBlockEventValidator);
	}

	@RequestMapping(value = "/adminblockEvent/{id}")
	public String adminBlockSeat(@PathVariable String id, Model m) {
		Event event = blockSeatService.getEventById(id);
		EventSeatBlockDtl eventSeatBlockDtl = mapEventBlockByEvent.getMappedeventSeatBlock(event);
		m.addAttribute("command", "");
		m.addAttribute("errorMessage", "");
		m.addAttribute("eventSeatBlockDtl", eventSeatBlockDtl);
		return "AdminBlockSeat";
	}

	@RequestMapping(value = "/blockSeat", method = RequestMethod.POST)
	public String adminUpdateSeInDb(@ModelAttribute("eventSeatBlockDtl") @Validated EventSeatBlockDtl eventSeatBlockDtl,
			BindingResult br, Model m) {
		if (br.hasErrors()) {
			m.addAttribute("command", eventSeatBlockDtl);
			m.addAttribute("Success", "");
			m.addAttribute("errorMessage", "");
			return "AdminBlockSeat";
		}
		if (GetSeatCountService.getBlockedSeatCount(eventSeatBlockDtl)
				+ Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked()) > 5) {
			m.addAttribute("command", eventSeatBlockDtl);
			m.addAttribute("Success", "");
			m.addAttribute("errorMessage", "User Exceeded maximum seatbooking for the event");
			return "AdminBlockSeat";
		}
		Event event = blockSeatService.getEventById(eventSeatBlockDtl.getGameEventId());
		event = mapEventBlockByEvent.UpdateSeatCount(event, eventSeatBlockDtl, false);
		blockSeatService.updateEventSeatCount(event);
		eventSeatBlockDtl
				.setTotalSeatBooked((String.valueOf(GetSeatCountService.getBlockedSeatCount(eventSeatBlockDtl))));
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateEventBookingDate(eventSeatBlockDtl);
		String successMessage = "Seat is blocked for the Game Event by the User Successfully and the confirmation date is"
				+ eventSeatBlockDtl.getConfirmationDate();
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalBookingCount(eventSeatBlockDtl, false);
		eventSeatBlockDtl
				.setCancelledSeatCount(String.valueOf(GetSeatCountService.getUnBlockedSeatCount(eventSeatBlockDtl)));
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalCancelledSeatCount(eventSeatBlockDtl, false);
		blockSeatService.upsert(eventSeatBlockDtl);
		event.setBookingStatus(bookingSoftBlocked);
		if (event.getNumberOfSeats().equals("0")) {
			event.setBookingStatus(bookingNotAvailable);
		}
		blockSeatService.updateEventBookingStatus(event);
		m.addAttribute("Success", successMessage);
		m.addAttribute("command", "");
		m.addAttribute("errorMessage", "");
		EventSeatBlockDtl eventSeatBlockDtlUpd = mapEventBlockByEvent.getMappedeventSeatBlock(event);
		eventSeatBlockDtlUpd = mapEventBlockByEvent.UpdateUserInfo(eventSeatBlockDtlUpd);
		eventSeatBlockDtlUpd.setTotalSeatBooked(eventSeatBlockDtl.getTotalSeatBooked());
		m.addAttribute("eventSeatBlockDtl", eventSeatBlockDtlUpd);
		return "AdminBlockSeat";
	}

}
