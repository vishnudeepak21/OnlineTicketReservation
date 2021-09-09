package com.ticketbooking.beans;

public class EventSeatBlockDtl {
	
	private String gameEventId = "";
	private String gameEventName = "";
	private String showDate = "";
	private String showStatus = "";
	private String userId = "";
	private String userName = "";
	private String softBlockingDate = "";
	private String confirmationDate = "";
	private String seatCancelledDate = "";
	private String totalSeatBooked = "";
	private String actualSeatBooked = "";
	private String cancelledSeatCount = "";
	private String actualCancelledSeatCount ="";
	private String numberOfSeats = "";
	
	public String getGameEventId() {
		return gameEventId;
	}
	public void setGameEventId(String gameEventId) {
		this.gameEventId = gameEventId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSoftBlockingDate() {
		return softBlockingDate;
	}
	public void setSoftBlockingDate(String softBlockingDate) {
		this.softBlockingDate = softBlockingDate;
	}
	public String getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(String confirmationDate) {
		this.confirmationDate = confirmationDate;
	}
	public String getSeatCancelledDate() {
		return seatCancelledDate;
	}
	public void setSeatCancelledDate(String seatCancelledDate) {
		this.seatCancelledDate = seatCancelledDate;
	}
	public String getTotalSeatBooked() {
		return totalSeatBooked;
	}
	public void setTotalSeatBooked(String totalSeatBooked) {
		this.totalSeatBooked = totalSeatBooked;
	}
	public String getCancelledSeatCount() {
		return cancelledSeatCount;
	}
	public void setCancelledSeatCount(String cancelledSeatCount) {
		this.cancelledSeatCount = cancelledSeatCount;
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
	public String getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(String numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public String getActualSeatBooked() {
		return actualSeatBooked;
	}
	public void setActualSeatBooked(String actualSeatBooked) {
		this.actualSeatBooked = actualSeatBooked;
	}
	public String getActualCancelledSeatCount() {
		return actualCancelledSeatCount;
	}
	public void setActualCancelledSeatCount(String actualCancelledSeatCount) {
		this.actualCancelledSeatCount = actualCancelledSeatCount;
	}
	@Override
	public String toString() {
		return "EventSeatBlockDtl [gameEventId=" + gameEventId + ", gameEventName=" + gameEventName + ", showDate="
				+ showDate + ", showStatus=" + showStatus + ", userId=" + userId + ", userName=" + userName
				+ ", softBlockingDate=" + softBlockingDate + ", confirmationDate=" + confirmationDate
				+ ", seatCancelledDate=" + seatCancelledDate + ", totalSeatBooked=" + totalSeatBooked
				+ ", actualSeatBooked=" + actualSeatBooked + ", cancelledSeatCount=" + cancelledSeatCount
				+ ", actualCancelledSeatCount=" + actualCancelledSeatCount + ", numberOfSeats=" + numberOfSeats + "]";
	}	
	
	
	
}
