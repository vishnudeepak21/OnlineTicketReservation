package com.ticketbooking.Service;

import com.ticketbooking.beans.Event;

public interface DeleteEventService {
	
	Event getEventById(String id);
	
	int deleteEventById(Event event);

}
