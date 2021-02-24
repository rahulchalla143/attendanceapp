package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.SessionTrainerMap;

public class SessionTrainerMapTest {
	
	SessionTrainerMap auth = new SessionTrainerMap();
	SessionTrainerMap auth1 = new SessionTrainerMap(1,2,1);
	
	

	@Test
	void testSessionid() {
		auth.setSessionid(123);
		assertEquals(auth.getSessionid(), 123);
	}

	@Test
	void testSessionTrainerid() {
		auth.setSessiontrainerid(2);
		assertEquals(auth.getSessiontrainerid(), 2);
	}

	@Test
	void testTrainerid() {
		auth.setTrainerid(1);
		assertEquals(auth.getTrainerid(),1);
	}
	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(),string);
	}

}
	
	
	
	
	
	
	


