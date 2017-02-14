package com.parkinglot.service;

import java.util.List;

import com.parkinglot.model.Car;
import com.parkinglot.model.Ticket;

public interface ParkingLot {
	public Ticket park(final Car car);
	public boolean leaveParking(Ticket ticket);
	public List<Ticket> status();
	public List<String> getRegistrationNumberForColour(String colour);
	public String getSlotForNumber(String registrationNumber);
	public List<String> getSlotsForColour(String colour);
}
