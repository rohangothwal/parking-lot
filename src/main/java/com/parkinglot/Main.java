package com.parkinglot;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.parkinglot.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.parkinglot.constants.Constants;
import com.parkinglot.constants.ParkingInstructions;
import com.parkinglot.model.Car;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Ticket;
import com.parkinglot.service.TicketingService;
import com.parkinglot.service.impl.TicketingServiceImpl;

//java -Dlog4j.configuration=log4j.xml -Xms1g -Xmx4g -jar parking-lot-jar-with-dependencies.jar
//java -Xms1g -Xmx4g -jar parking-lot-jar-with-dependencies.jar
public class Main {

	private static Logger	log	= (Logger) LoggerFactory.getLogger(Main.class);
	
	private static TicketingService service;

	public static void main(String[] args) {
		if (args.length == 0) {
			startInterActiveMode();
		} else {
			File sourceFile = new File(args[0]);
			if (!sourceFile.exists()) {
				log.error("No input file found");
				return;
			}
			startProcessingFromFile(sourceFile);
		}
	}

	private static void initializeSystem(int parkingCapacity){
		ParkingLot parkingLot = new ParkingLot(parkingCapacity);
		service = new TicketingServiceImpl(parkingLot);
	}

	private static String processInstructions(String[] instructionSet) {
		ParkingInstructions instruction = ParkingInstructions.getInstructionFor(instructionSet[0]);

		try {
			switch (instruction) {
			case CREATE_PARKING_LOT: {
				int parkingCapacity = Integer.parseInt(instructionSet[1]);
				initializeSystem(parkingCapacity);
				return "Created a parking lot with "+parkingCapacity+" slots";
			}
			case LEAVE:	{
				Ticket ticket = new Ticket();
				ticket.setParkingSlot(Integer.parseInt(instructionSet[1]));
				Car car = service.leaveParking(ticket);
				if (car == null) {
					return Constants.INVALID_TICKET;
				} else {
					return "Slot number " +ticket.getParkingSlot()+ " is free";
				}
			}
			case PARK: {
				Car car = new Car(instructionSet[1].trim(), instructionSet[2].trim());
				Ticket ticket = service.park(car);
				if (ticket == null) {
					return Constants.PARKING_LOT_FULL;
				} else {
					return "Allocated slot number: "+ticket.getParkingSlot();
				}
			}
			case REGISTRATION_NUMBER_FOR_COLOUR: {
				String colour = instructionSet[1].trim();
				List<String> list = service.getRegistrationNumberForColour(colour);
				if (list == null) {
					return Constants.NOT_FOUND;
				} else {
					return StringUtils.join(list.iterator(), ",");
				}
			}
			case SLOT_NUMBER_FOR_COLOUR: {
				String colour = instructionSet[1].trim();
				List<Integer> list = service.getSlotsForColour(colour);
				if (list == null) {
					return Constants.NOT_FOUND;
				} else {
					return StringUtils.join(list.iterator(), ",");
				}
			}
			case SLOT_NUMBER_FOR_REG_NUMBER: {
				int slot = service.getSlotForNumber(instructionSet[1].trim());
				if (slot == 0) {
					return Constants.NOT_FOUND;
				} else {
					return ""+slot;
				}
			}
			case STATUS: {
				List<Ticket> tickets = service.status();
				return Constants.STATUS_HEADER + StringUtils.join(tickets.iterator(), "\n");
			}
			}
		} catch(Exception e) {
			log.error("Invalid Input");
			return "Invalid Input";
		}
		return null;
	}

	private static void startInterActiveMode() {
		Console console = System.console();
		console.printf("Please input commands. Type \"Exit\" to Exit\n");
		String instruction = null;
		while (true) {
			instruction = console.readLine();
			if (instruction != null && instruction.equalsIgnoreCase("Exit")) {
				break;
			}
			console.printf(processInstructions(instruction.split(" ")));
			console.printf("\n");
			console.printf("\n");
		}
	}

	private static void startProcessingFromFile(File sourceFile) {

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(sourceFile));
			for (String instruction = bufferedReader.readLine(); instruction != null; instruction = bufferedReader.readLine()) {
				System.out.println(processInstructions(instruction.split(" ")));
				System.out.println();
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
