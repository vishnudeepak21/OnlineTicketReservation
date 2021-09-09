package com.ticketbooking.Service;

import com.ticketbooking.beans.EventSeatBlockDtl;

public interface GetSeatCountService {
	
	int getBlockedSeatCount(EventSeatBlockDtl eventSeatBlockDtl);
	
	int getUnBlockedSeatCount(EventSeatBlockDtl eventSeatBlockDtl);
	

}
