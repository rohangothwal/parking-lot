package com.parkinglot.model.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.parkinglot.config.AppConfig;
import com.parkinglot.model.Car;
import com.parkinglot.model.ParkingLot;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class,loader=AnnotationConfigContextLoader.class)
public class ParkingLotTest {
	
	private ParkingLot parkinglot;
	private Car car1 = new Car("KA-01-HH-1234","White");
	private Car car2 = new Car("KA-01-HH-9999","White");
	private Car car3 = new Car("KA-01-BB-0001","Black");
	private Car car4 = new Car("KA-01-HH-7777","Red");
	private Car car5 = new Car("KA-01-HH-2701","Blue");
	private Car car6 = new Car("KA-01-HH-3141","Black");
	private Car car7 = new Car("KA-01-P-333","White");
	private Car car8 = new Car("DL-12-AA-9999","White");
	
	@Before
	public void init(){
		parkinglot = new ParkingLot(6);
	}
	
	@Test
	public void modelTest(){
		
		//slots are assigned in sequence
		assertEquals(0, parkinglot.assignNextSlot(car1));
		assertEquals(1, parkinglot.assignNextSlot(car2));
		assertEquals(2, parkinglot.assignNextSlot(car3));
		assertEquals(3, parkinglot.assignNextSlot(car4));
		assertEquals(4, parkinglot.assignNextSlot(car5));
		assertEquals(5, parkinglot.assignNextSlot(car6));
		
		//parking is full
		assertEquals(-1, parkinglot.assignNextSlot(car7));
		assertEquals(-1, parkinglot.assignNextSlot(car8));
		
		//cars leaving parking
		assertEquals(car2, parkinglot.clearSlot(1));
		assertEquals(car4, parkinglot.clearSlot(3));
		
		//out of 2 vacant slot the 1 assigned is near entry
		assertEquals(1, parkinglot.assignNextSlot(car4));
		
		//which car is parked on which slot
		assertEquals(car4, parkinglot.getCarOnSlot(1));
		
		//checking count of cars parked
		assertEquals(car4, parkinglot.clearSlot(1));
		assertEquals(4,parkinglot.getAllCarsParked().size());
		
		//checking index for registration number
		assertEquals(0,parkinglot.getSlotForRegistrationNumber(car1.getRegistrationNumber()));
		assertEquals(2,parkinglot.getSlotForRegistrationNumber(car3.getRegistrationNumber()));
		assertEquals(5,parkinglot.getSlotForRegistrationNumber("ka-01-HH-3141"));
		assertEquals(-1,parkinglot.getSlotForRegistrationNumber("aa-01-HH-3141"));
		
		//checking index for colour
		assertEquals(2,parkinglot.getSlotsForColour("black").size());
		assertEquals(1,parkinglot.getSlotsForColour("white").size());
		assertEquals(0,parkinglot.getSlotsForColour("RED").size());
		assertEquals(1,parkinglot.getSlotsForColour("blue").size());
	}
}
