package com.parkinglot;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.BaseMatcher.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.parkinglot.config.AppConfig;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class,loader=AnnotationConfigContextLoader.class)
public class MainTest {
	private static Logger	log	= (Logger) LoggerFactory.getLogger(MainTest.class);
		@Before
		public void init(){}
		
		@Test
		public void testMain(){
			log.debug("Hey! I have started testing");
			assertEquals(Main.getHelloWorld(), "Hello World");
		}

}
