package com.ticketbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ticketbooking.beans.Event;
import com.ticketbooking.dao.EventDao;

@Service
public class DeleteEventServiceImpl implements DeleteEventService {

	@Autowired
	private EventDao eventDao;

	@Value("${game.booking.status.NotAvailable}")
	private String bookingNotAvailable;
	@Value("${game.status.inactive}")
	private String statusInActive;

	@Override
	public Event getEventById(String id) {
		return eventDao.getEventById(id);
	}

	@Override
	public int deleteEventById(Event event) {
		event.setBookingStatus(bookingNotAvailable);
		event.setShowStatus(statusInActive);
		return eventDao.deleteEventById(event);
	}

}
