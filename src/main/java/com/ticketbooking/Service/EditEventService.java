package com.ticketbooking.Service;

import com.ticketbooking.beans.Event;

public interface EditEventService {
	
	Event getEventById(String id);
	
	void UpdateEventById(Event event);

}
