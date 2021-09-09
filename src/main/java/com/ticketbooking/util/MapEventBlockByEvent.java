package com.ticketbooking.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;

@Component
public class MapEventBlockByEvent {

	@Value("${userName}")
	private String userName;
	@Value("${userRegistrationNumber}")
	private String userRegistrationNumber;
	@Value("${datePattern}")
	private String dateFormat;

	public EventSeatBlockDtl getMappedeventSeatBlock(Event event) {

		EventSeatBlockDtl eventSeatBlockDtl = new EventSeatBlockDtl();
		eventSeatBlockDtl.setGameEventId(event.getGameEventId());
		eventSeatBlockDtl.setGameEventName(event.getGameEventName());
		eventSeatBlockDtl.setShowDate(event.getShowDate());
		eventSeatBlockDtl.setShowStatus(event.getShowStatus());
		eventSeatBlockDtl.setNumberOfSeats(event.getNumberOfSeats());

		return eventSeatBlockDtl;
	}

	public Event getMappedeventSeatBlock(EventSeatBlockDtl eventSeatBlockDtl) {

		Event event = new Event();
		event.setGameEventId(event.getGameEventId());
		event.setGameEventName(event.getGameEventName());
		event.setShowDate(event.getShowDate());
		event.setShowStatus(event.getShowStatus());
		event.setNumberOfSeats(event.getNumberOfSeats());

		return event;
	}

	public Event UpdateSeatCount(Event event, EventSeatBlockDtl eventSeatBlockDtl, boolean unblockIc) {
		event.setNumberOfSeats(unblockIc
				? String.valueOf(Integer.parseInt(eventSeatBlockDtl.getActualCancelledSeatCount())
						+ Integer.parseInt(event.getNumberOfSeats()))
				: String.valueOf(Integer.parseInt(event.getNumberOfSeats())
						- Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked())));
		return event;
	}

	public EventSeatBlockDtl UpdateUserInfo(EventSeatBlockDtl eventSeatBlockDtl) {
		eventSeatBlockDtl.setUserId(userRegistrationNumber);
		eventSeatBlockDtl.setUserName(userName);
		return eventSeatBlockDtl;
	}

	public EventSeatBlockDtl UpdateEventBookingDate(EventSeatBlockDtl eventSeatBlockDtl) {
		eventSeatBlockDtl.setSoftBlockingDate(new SimpleDateFormat(dateFormat).format(new Date()));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		eventSeatBlockDtl.setConfirmationDate(LocalDate.now().plusDays(15).format(formatter));
		return eventSeatBlockDtl;
	}

	public EventSeatBlockDtl UpdateCancelBookingDate(EventSeatBlockDtl eventSeatBlockDtl) {
		eventSeatBlockDtl.setSeatCancelledDate(new SimpleDateFormat(dateFormat).format(new Date()));
		return eventSeatBlockDtl;
	}

	public EventSeatBlockDtl UpdateTotalBookingCount(EventSeatBlockDtl eventSeatBlockDtl, boolean unblockIc) {
		System.out.println(eventSeatBlockDtl.getActualCancelledSeatCount());
		eventSeatBlockDtl.setTotalSeatBooked(!(unblockIc)
				? String.valueOf(Integer.parseInt(eventSeatBlockDtl.getTotalSeatBooked())
						+ Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked()))
				: String.valueOf(Integer.parseInt(eventSeatBlockDtl.getTotalSeatBooked())
						- Integer.parseInt(eventSeatBlockDtl.getActualCancelledSeatCount())));
		return eventSeatBlockDtl;
	}

	public EventSeatBlockDtl UpdateTotalCancelledSeatCount(EventSeatBlockDtl eventSeatBlockDtl, boolean unblockIc) {
		try {
			if (unblockIc) {
				eventSeatBlockDtl.setCancelledSeatCount(
						String.valueOf(Integer.parseInt(eventSeatBlockDtl.getCancelledSeatCount())
								+ Integer.parseInt(eventSeatBlockDtl.getActualCancelledSeatCount())));
			} else {
				if (Integer.parseInt(eventSeatBlockDtl.getCancelledSeatCount()) > 0) {
					if (Integer.parseInt(eventSeatBlockDtl.getCancelledSeatCount())
							- Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked()) >= 0) {
						eventSeatBlockDtl.setCancelledSeatCount(
								String.valueOf(Integer.parseInt(eventSeatBlockDtl.getCancelledSeatCount())
										- Integer.parseInt(eventSeatBlockDtl.getActualSeatBooked())));
					} else {
						eventSeatBlockDtl.setCancelledSeatCount("0");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return eventSeatBlockDtl;
		}
		return eventSeatBlockDtl;
	}

}
