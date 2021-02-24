package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.SessionSkillMap;

public class SessionSkillMapTest {

	SessionSkillMap auth = new SessionSkillMap();
	SessionSkillMap auth1 = new SessionSkillMap(1,2,3);
	

	@Test
	void testSessionid() {
		auth.setSessionid(123);
		assertEquals(auth.getSessionid(), 123);
	}

	@Test
	void testSessionskillid() {
		auth.setSessionskillid(2);
		assertEquals(auth.getSessionskillid(), 2);
	}

	@Test
	void testSkillid() {
		auth.setSkillid(3);
		assertEquals(auth.getSkillid(),3);
	}
	
	
	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(),string);
	}

}
	
	
	
	

