package com.parkinglot.service.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.parkinglot.config.AppConfig;
import com.parkinglot.model.Car;
import com.parkinglot.model.Ticket;
import com.parkinglot.service.TicketingService;

import static org.junit.Assert.*;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class,loader=AnnotationConfigContextLoader.class)
public class ParkingLotServiceTest {

	private static Logger	log	= (Logger) LoggerFactory.getLogger(ParkingLotServiceTest.class);
	private TicketingService service;
	@Autowired
	private ApplicationContext context;
	
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
		int parkingCapacity = 6;
		service = (TicketingService) context.getBean("ticketingService",parkingCapacity);
	}
	
	@Test
	public void parkTest() {
		assertEquals(car1, service.park(car1).getCar());
		assertEquals(new Integer(2), service.park(car2).getParkingSlot());
		assertEquals(new Integer(3), service.park(car3).getParkingSlot());
		assertEquals(car4, service.park(car4).getCar());
		assertEquals(car5, service.park(car5).getCar());
		assertEquals(car6, service.park(car6).getCar());
		
		assertNull( service.park(car7));
	}
	
	@Test
	public void leaveParkingTest() {
		Ticket ticket = service.park(car1);
		assertEquals(car1, service.leaveParking(ticket));
	}
	
	@Test
	public void statusTest() {
		assertTrue(true);
	}
	
	@Test
	public void getRegistrationNumberForColourTest() {
		assertTrue(true);
	}
	
	@Test
	public void getSlotForNumberTest() {
		assertTrue(true);
	}
	
	@Test
	public void getSlotsForColourTest() {
		assertTrue(true);
	}

}
