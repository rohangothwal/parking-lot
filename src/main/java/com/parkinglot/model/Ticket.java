package com.parkinglot.model;

public class Ticket {
	private Integer ticketId;
	private Car car;
	private Integer parkingSlot;
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Integer getParkingSlot() {
		return parkingSlot;
	}
	public void setParkingSlot(Integer parkingSlot) {
		this.parkingSlot = parkingSlot;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", car=" + car + ", parkingSlot=" + parkingSlot + "]";
	}
	
}
