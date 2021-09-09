package com.ticketbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.beans.Event;
import com.ticketbooking.dao.EventDao;

@Service
public class AddEventImpl implements AddEvent {

	@Autowired
	private EventDao eventDao;

	@Override
	public void addEvent(Event event) {
		eventDao.saveEvent(event);
	}

}
