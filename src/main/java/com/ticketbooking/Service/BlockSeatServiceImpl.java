package com.ticketbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;
import com.ticketbooking.dao.EventBlockDao;
import com.ticketbooking.dao.EventDao;

@Service
public class BlockSeatServiceImpl implements BlockSeatService {

	@Autowired
	private EventBlockDao eventBlockDao;

	@Autowired
	private EventDao eventDao;

	@Override
	public int upsert(EventSeatBlockDtl eventSeatBlock) {
		return eventBlockDao.upsert(eventSeatBlock);
	}

	@Override
	public Event getEventById(String id) {
		return eventDao.getEventById(id);
	}

	@Override
	public int updateEventSeatCount(Event event) {
		return eventDao.updateEventSeatCount(event);
	}

	@Override
	public int updateEventBookingStatus(Event event) {
		return eventDao.updateBookingStatus(event);
	}

}
