package com.parkinglot.constants;

import java.util.HashMap;
import java.util.Map;

public enum ParkingInstructions {

	CREATE_PARKING_LOT("create_parking_lot"),
	PARK("park"),
	LEAVE("leave"),
	STATUS("status"),
	SLOT_NUMBER_FOR_COLOUR("slot_numbers_for_cars_with_colour"),
	REGISTRATION_NUMBER_FOR_COLOUR("registration_numbers_for_cars_with_colour"),
	SLOT_NUMBER_FOR_REG_NUMBER("slot_number_for_registration_number");

	private final String value;

	private ParkingInstructions(String value) {
		this.value = value;
	}

	private static Map<String, ParkingInstructions> instructionMap;
	static {
		instructionMap = new HashMap<>();

		for (ParkingInstructions instruction : values()){
			instructionMap.put(instruction.value, instruction);
		}
	}

	public static ParkingInstructions getInstructionFor(String instruction) {
		return instructionMap.get(instruction.toLowerCase());
	}
}
