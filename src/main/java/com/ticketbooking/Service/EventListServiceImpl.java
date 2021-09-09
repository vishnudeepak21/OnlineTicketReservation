package com.ticketbooking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;
import com.ticketbooking.dao.EventDao;

@Service
public class EventListServiceImpl implements EventListService {

	@Autowired
	private EventDao eventDao;

	@Override
	public List<Event> eventList() {
		return eventDao.getEventList();
	}

	@Override
	public List<Event> eventListByDate() {
		return eventDao.getEventListByWeek();
	}

	@Override
	public List<Event> eventListByName(String name) {
		return eventDao.getEventListByName(name);
	}

	@Override
	public List<EventSeatBlockDtl> eventSoftBlockDtl(String id) {
		return eventDao.getSoftBlockDetails(id);
	}

}
