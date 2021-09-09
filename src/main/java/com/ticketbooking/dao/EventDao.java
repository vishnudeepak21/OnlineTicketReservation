package com.ticketbooking.dao;

import java.util.List;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;

public interface EventDao {

	String getMaxId();

	int saveEvent(Event event);

	List<Event> getEventList();

	Event getEventById(String id);

	int updateEventById(Event event);

	int deleteEventById(Event event);

	List<Event> getEventListByWeek();

	List<Event> getEventListByName(String name);

	int updateEventSeatCount(Event event);

	int updateBookingStatus(Event event);

	List<EventSeatBlockDtl> getSoftBlockDetails(String id);

}
