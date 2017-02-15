package com.parkinglot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.parkinglot.config.AppConfig;
import com.parkinglot.service.TicketingService;

//java -Dlog4j.configuration=log4j.xml -Xms1g -Xmx4g -jar parking-lot-jar-with-dependencies.jar
//java -Xms1g -Xmx4g -jar parking-lot-jar-with-dependencies.jar
public class Main {
	
	private static Logger	log	= (Logger) LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		 ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 int parkingCapacity =6 ;
		 TicketingService service = (TicketingService) context.getBean("ticketingService",parkingCapacity);
	}

}
