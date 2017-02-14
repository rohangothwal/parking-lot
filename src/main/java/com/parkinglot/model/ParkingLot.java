package com.parkinglot.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingLot {
	
	private static Logger	log	= (Logger) LoggerFactory.getLogger(ParkingLot.class); 
	
	private Car[] slots;
	private int firstUnOccupiedSlot;
	private Map<String, Set<Integer>> colourIndex;
	private Map<String, Integer> registrationNumberIndex;
	
	public ParkingLot(int size) {
		slots = new Car[size];
		colourIndex = new HashMap<>();
		registrationNumberIndex = new HashMap<>();
	}
	
	public int assignNextSlot(Car car) {
		if (firstUnOccupiedSlot==slots.length) {
			return -1;
		}
		int slotAssigned = firstUnOccupiedSlot;
		slots[firstUnOccupiedSlot++] = car;
		
		addIndexes(slotAssigned, car);
		
		for (int i=firstUnOccupiedSlot;i<slots.length;i++) {
			if (slots[i] == null) {
				firstUnOccupiedSlot = i;
				log.trace("Slot assigned : "+slotAssigned);
				return slotAssigned;
			}
		}
		firstUnOccupiedSlot = slots.length;
		log.trace("Slot assigned : "+slotAssigned);
		return slotAssigned;
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

	public Car clearSlot(int slot) {
		
		if (slot>=slots.length || slots[slot] == null) {
			return null;
		}
		Car car = slots[slot];
		slots[slot] = null;
		log.trace("Removing car : "+car);
		removeIndexes(slot, car);
		
		if (firstUnOccupiedSlot > slot) {
			firstUnOccupiedSlot = slot;
		}
		log.trace("UnOccupied Slot assigned : "+firstUnOccupiedSlot);
		return car;
	}
	
	private void removeIndexes(int slot, Car car) {
		colourIndex.get(car.getColour().toLowerCase()).remove(slot);
		registrationNumberIndex.remove(car.getRegistrationNumber().toLowerCase());
	}

	public int getSlotForRegistrationNumber(String registrationNumber) {
		if (registrationNumber != null && registrationNumberIndex.containsKey(registrationNumber.trim().toLowerCase())) {
			return registrationNumberIndex.get(registrationNumber.trim().toLowerCase());
		} else {
			return -1;
		}
	}
	
	public Set<Integer> getSlotsForColour(String colour) {
		if (colour != null) {
			return colourIndex.get(colour.trim().toLowerCase());
		} else {
			return null;
		}
	}
	
	public Car getCarOnSlot(int slot) {
		return slots[slot];
	}
	
	public List<Car> getAllCarsParked() {
		List<Car> cars = new ArrayList<>();
		for (Integer i : registrationNumberIndex.values()) {
			cars.add(slots[i]);
		}
		return cars;
	}
}
