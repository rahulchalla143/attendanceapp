package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.Skill;

public class SkillTest {
	
	Skill auth = new Skill();
	Skill auth1 = new Skill(1,"ab","ab");

	@Test
	void testSkillid() {
		auth.setSkillid(123);
		assertEquals(auth.getSkillid(), 123);
	}

	@Test
	void testSkilltype() {
		auth.setSkilltype("ab");
		assertEquals(auth.getSkilltype(), "ab");
	}

	@Test
	void testSkilldesc() {
		auth.setSkilldesc("ab");
		assertEquals(auth.getSkilldesc(),"ab");
	}
	
	
	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(),string);
	}

}
	
	
	
	
	
	
	


	
	


