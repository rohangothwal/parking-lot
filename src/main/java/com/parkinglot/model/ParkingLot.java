package com.parkinglot.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingLot {
	
	private static Logger	log	= (Logger) LoggerFactory.getLogger(ParkingLot.class); 
	
	private Car[] slots;
	private Map<String, Set<Integer>> colourIndex;
	private Map<String, Integer> registrationNumberIndex;
	private PriorityQueue<Integer> emptySlots;
	
	public ParkingLot(int size) {
		slots = new Car[size];
		colourIndex = new HashMap<>();
		registrationNumberIndex = new HashMap<>();
		emptySlots = new PriorityQueue<>();
		for (int i=0;i<size;i++) {
			emptySlots.add(i);
		}
	}
	
	/**
	 * Assigns next slot nearest to parking entrance
	 * @param car
	 * @return slot assigned or -1 if no slot is available
	 */
	public int assignNextSlot(Car car) {
		Integer firstUnOccupiedSlot = emptySlots.poll();
		if (firstUnOccupiedSlot==null) {
			return -1;
		}
		slots[firstUnOccupiedSlot] = car;
		addIndexes(firstUnOccupiedSlot, car);
		
		return firstUnOccupiedSlot;
	}
	
	private void addIndexes(int slotAssigned, Car car) {
		
		if (colourIndex.containsKey(car.getColour().toLowerCase())) {
			colourIndex.get(car.getColour().toLowerCase()).add(slotAssigned);
		} else {
			Set<Integer> set = new HashSet<>();
			set.add(slotAssigned);
			colourIndex.put(car.getColour().toLowerCase(), set);
		}
		
		registrationNumberIndex.put(car.getRegistrationNumber().toLowerCase(), slotAssigned);
		
	}

	/**
	 * Clears the given slot
	 * @param slot
	 * @return Car parked on given slot, null if no car is parked or slot number is invalid
	 */
	public Car clearSlot(int slot) {
		
		if (slot>=slots.length || slots[slot] == null) {
			return null;
		}
		Car car = slots[slot];
		slots[slot] = null;
		removeIndexes(slot, car);
		emptySlots.add(slot);
		return car;
	}
	
	private void removeIndexes(int slot, Car car) {
		colourIndex.get(car.getColour().toLowerCase()).remove(slot);
		registrationNumberIndex.remove(car.getRegistrationNumber().toLowerCase());
	}

	/**
	 * Returns slot number for the car with registration number
	 * @param registrationNumber
	 * @return slot or -1 in case of invalid registration number or car not found
	 */
	public int getSlotForRegistrationNumber(String registrationNumber) {
		if (registrationNumber != null && registrationNumberIndex.containsKey(registrationNumber.trim().toLowerCase())) {
			return registrationNumberIndex.get(registrationNumber.trim().toLowerCase());
		} else {
			return -1;
		}
	}
	
	/**
	 * Returns slots for car with given colour, Slot numbers start from 0
	 * @param colour
	 * @return Set of slots or null in case of invalied colour
	 */
	public Set<Integer> getSlotsForColour(String colour) {
		if (colour != null) {
			return colourIndex.get(colour.trim().toLowerCase());
		} else {
			return null;
		}
	}
	
	/**
	 * Returns car parked on given slot, starting from 0
	 * @param slot
	 * @return Car parked on given slot or null if no car is parked
	 */
	public Car getCarOnSlot(int slot) {
		return slots[slot];
	}
	
	/**
	 * 
	 * @return Status of all slots in parking lot
	 */
	public Car[] getAllCarsParked() {
		return slots;
	}
}
