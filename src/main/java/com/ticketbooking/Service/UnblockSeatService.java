package com.ticketbooking.Service;

import com.ticketbooking.beans.Event;
import com.ticketbooking.beans.EventSeatBlockDtl;

public interface UnblockSeatService {
	
	int updateCancellationSeat(EventSeatBlockDtl eventSeatBlock);
	
    Event getEventById(String id);
    
    int updateEventSeatCount(Event event);
    
    int updateEventBookingStatus(Event event);
 
    int checkCustomerBookingStatus(EventSeatBlockDtl eventSeatBlock);
    
}
