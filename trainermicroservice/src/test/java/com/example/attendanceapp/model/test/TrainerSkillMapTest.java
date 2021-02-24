package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.TrainerSkillMap;

public class TrainerSkillMapTest {

	TrainerSkillMap auth = new TrainerSkillMap();
	TrainerSkillMap auth1 = new TrainerSkillMap(1,2,3);
	

	@Test
	void testTrainerid() {
		auth.setTrainerid(123);
		assertEquals(auth.getTrainerid(), 123);
	}

	@Test
	void testTrainerskillid() {
		auth.setTrainerskillid(2);
		assertEquals(auth.getTrainerskillid(), 2);
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
	
	
	
	

