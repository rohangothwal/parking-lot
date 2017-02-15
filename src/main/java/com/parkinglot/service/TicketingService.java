package com.parkinglot.service;

import java.util.List;

import com.parkinglot.model.Car;
import com.parkinglot.model.Ticket;

public interface TicketingService {
	
	/**
	 * Generates a ticket for next available slot, slots are assigned starting from 1
	 * @param car
	 * @return Ticket or null if no slot is available
	 */
	public Ticket park(final Car car);
	
	/**
	 * Clears slot for given ticket
	 * @param ticket
	 * @return Car leaving or null if ticket in invalid
	 */
	public Car leaveParking(Ticket ticket);
	
	/**
	 * Returns details of all the cars parked in parking lot
	 * @return List of tickets issued
	 */
	public List<Ticket> status();
	
	/**
	 * 
	 * @param colour
	 * @return List of RegistrationsNumbers for given colour, null if no car with given colour is parked
	 */
	public List<String> getRegistrationNumberForColour(String colour);
	
	/**
	 * 
	 * @param registrationNumber
	 * @return Slot for given registration number, 0 if not present
	 */
	public int getSlotForNumber(String registrationNumber);
	
	/**
	 * 
	 * @param colour
	 * @return List of slots for given colour of car, null if no car with given colour is parked
	 */
	public List<Integer> getSlotsForColour(String colour);
}
