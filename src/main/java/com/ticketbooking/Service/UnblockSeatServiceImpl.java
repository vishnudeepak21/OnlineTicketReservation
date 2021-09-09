package com.ticketbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;
import com.ticketbooking.dao.EventBlockDao;
import com.ticketbooking.dao.EventDao;

@Service
public class UnblockSeatServiceImpl implements UnblockSeatService {

	@Autowired
	private EventBlockDao eventBlockDao;

	@Autowired
	private EventDao eventDao;

	@Override
	public int updateCancellationSeat(EventSeatBlockDtl eventSeatBlock) {
		return eventBlockDao.updateCancellationSeat(eventSeatBlock);
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

	@Override
	public int checkCustomerBookingStatus(EventSeatBlockDtl eventSeatBlock) {
		return eventBlockDao.checkUserBlockedForEvent(eventSeatBlock);
	}

}
