package com.ticketbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.beans.Event;
import com.ticketbooking.dao.EventDao;

@Service
public class EditEventServiceImpl implements EditEventService {

	@Autowired
	private EventDao eventDao;

	@Override
	public Event getEventById(String id) {
		return eventDao.getEventById(id);
	}

	@Override
	public void UpdateEventById(Event event) {
		eventDao.updateEventById(event);

	}

}
