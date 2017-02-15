package com.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.parkinglot.model.Car;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Ticket;
import com.parkinglot.service.TicketingService;

public class TicketingServiceImpl implements TicketingService {

	private ParkingLot parkingLot;
	
	private Set<Ticket> ticketsCache;
	
	public TicketingServiceImpl(ParkingLot parkingLot) {
		super();
		this.parkingLot = parkingLot;
		this.ticketsCache = new HashSet<>();
	}

	@Override
	public Ticket park(Car car) {

		int slot = parkingLot.assignNextSlot(car);
		
		if (slot == -1) {
			return null;
		}
		
		Ticket ticket = new Ticket();
		ticket.setCar(car);
		ticket.setParkingSlot(slot+1);
		ticketsCache.add(ticket);
		return ticket;
	}

	@Override
	public Car leaveParking(Ticket ticket) {
		ticketsCache.remove(ticket);
		return parkingLot.clearSlot(ticket.getParkingSlot()-1);
	}

	@Override
	public List<Ticket> status() {
		List<Ticket> list = new ArrayList<>();
		list.addAll(ticketsCache);
		return list;
	}

	@Override
	public List<String> getRegistrationNumberForColour(String colour) {
		List<String> list = new ArrayList<>();
		for (int i : parkingLot.getSlotsForColour(colour)) {
			list.add(parkingLot.getCarOnSlot(i).getRegistrationNumber());
		}
		return list;
	}

	@Override
	public int getSlotForNumber(String registrationNumber) {
		return parkingLot.getSlotForRegistrationNumber(registrationNumber)+1;
	}

	@Override
	public List<Integer> getSlotsForColour(String colour) {
		List<Integer> list = new ArrayList<>();
		for (int i : parkingLot.getSlotsForColour(colour)) {
			list.add(i+1);
		}
		return list;
	}

}
