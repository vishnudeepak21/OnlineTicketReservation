package com.ticketbooking.Service;

import java.util.List;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;


public interface EventListService {
	
	List<Event> eventList();
	
	List<Event> eventListByDate();
	
	List<Event> eventListByName(String name);
	
	List<EventSeatBlockDtl> eventSoftBlockDtl(String id);

}
