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
import com.ticketbooking.validator.UserSeatBlockEventValidator;

@Controller
public class BlockSeatController {

	@Autowired
	private BlockSeatService blockSeatService;
	@Autowired
	private MapEventBlockByEvent mapEventBlockByEvent;
	@Value("${game.booking.status.SoftBlocked}")
	private String bookingSoftBlocked;
	@Value("${game.booking.status.NotAvailable}")
	private String bookingNotAvailable;
	@Autowired
	private UserSeatBlockEventValidator userSeatBlockEventValidator;
	@Autowired
	private GetSeatCountService GetSeatCountService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userSeatBlockEventValidator);
	}

	@RequestMapping(value = "/userblockEvent/{id}")
	public String blockSeat(@PathVariable String id, Model m) {
		Event event = blockSeatService.getEventById(id);
		EventSeatBlockDtl eventSeatBlockDtl = mapEventBlockByEvent.getMappedeventSeatBlock(event);
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateUserInfo(eventSeatBlockDtl);
		eventSeatBlockDtl
				.setTotalSeatBooked((String.valueOf(GetSeatCountService.getBlockedSeatCount(eventSeatBlockDtl))));
		m.addAttribute("command", "");
		m.addAttribute("errorMessage", "");
		m.addAttribute("eventSeatBlockDtl", eventSeatBlockDtl);
		return "BlockSeat";
	}

	@RequestMapping(value = "/userBlockSeat", method = RequestMethod.POST)
	public String updateSeInDb(@ModelAttribute("eventSeatBlockDtl") @Validated EventSeatBlockDtl eventSeatBlockDtl,
			BindingResult br, Model m) {
		if (br.hasErrors()) {
			m.addAttribute("command", eventSeatBlockDtl);
			m.addAttribute("Success", "");
			m.addAttribute("errorMessage", "");
			return "BlockSeat";
		}
		Event event = blockSeatService.getEventById(eventSeatBlockDtl.getGameEventId());
		event = mapEventBlockByEvent.UpdateSeatCount(event, eventSeatBlockDtl, false);
		blockSeatService.updateEventSeatCount(event);
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateEventBookingDate(eventSeatBlockDtl);
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalBookingCount(eventSeatBlockDtl, false);
		eventSeatBlockDtl
				.setCancelledSeatCount(String.valueOf(GetSeatCountService.getUnBlockedSeatCount(eventSeatBlockDtl)));
		eventSeatBlockDtl = mapEventBlockByEvent.UpdateTotalCancelledSeatCount(eventSeatBlockDtl, false);
		String successMessage = "Seat is blocked for the User Successfully and the confirmation date is"
				+ eventSeatBlockDtl.getConfirmationDate();
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
		return "BlockSeat";
	}

}
