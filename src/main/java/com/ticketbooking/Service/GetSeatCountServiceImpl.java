package com.ticketbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.beans.EventSeatBlockDtl;
import com.ticketbooking.dao.EventBlockDao;

@Service
public class GetSeatCountServiceImpl implements GetSeatCountService {

	@Autowired
	private EventBlockDao eventBlockDao;

	@Override
	public int getBlockedSeatCount(EventSeatBlockDtl eventSeatBlockDtl) {
		return eventBlockDao.getBookedSeatCount(eventSeatBlockDtl);
	}

	@Override
	public int getUnBlockedSeatCount(EventSeatBlockDtl eventSeatBlockDtl) {
		return eventBlockDao.getCancelledSeatCount(eventSeatBlockDtl);
	}

}
