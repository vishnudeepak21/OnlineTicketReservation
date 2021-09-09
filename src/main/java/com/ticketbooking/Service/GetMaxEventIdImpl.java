package com.ticketbooking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.dao.EventDao;

@Service
public class GetMaxEventIdImpl implements GetMaxEventId {

	@Autowired
	private EventDao eventDao;

	@Override
	public String getMaxId() {
		return eventDao.getMaxId();
	}
}
