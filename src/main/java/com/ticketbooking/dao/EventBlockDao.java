package com.ticketbooking.dao;

import com.ticketbooking.beans.EventSeatBlockDtl;

public interface EventBlockDao {

	int upsert(EventSeatBlockDtl eventSeatBlock);

	int updateCancellationSeat(EventSeatBlockDtl eventSeatBlock);

	int getBookedSeatCount(EventSeatBlockDtl eventSeatBlock);

	int getCancelledSeatCount(EventSeatBlockDtl eventSeatBlock);

	int checkCustomerInfo(String id);

	int checkUserBlockedForEvent(EventSeatBlockDtl eventSeatBlock);

}
