package com.ticketbooking.beans;

public class Event {
	
	private String gameEventId = "";
	private String gameEventName = "";
	private String showDate = "";
	private String showStatus = "";
	private String bookingStatus = "";
	private String numberOfSeats = "";
	
	public String getGameEventId() {
		return gameEventId;
	}
	public void setGameEventId(String gameEventId) {
		this.gameEventId = gameEventId;
	}
	public String getGameEventName() {
		return gameEventName;
	}
	public void setGameEventName(String gameEventName) {
		this.gameEventName = gameEventName;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public String getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(String numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	
	@Override
	public String toString() {
		return "Event [gameEventId=" + gameEventId + ", gameEventName=" + gameEventName + ", showDate=" + showDate
				+ ", showStatus=" + showStatus + ", bookingStatus=" + bookingStatus + ", numberOfSeats=" + numberOfSeats
				+ "]";
	}	

}
