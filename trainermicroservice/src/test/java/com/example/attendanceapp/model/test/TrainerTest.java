package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.Trainer;

public class TrainerTest {
	
	Trainer auth =  new Trainer();
	Trainer auth1 =  new Trainer(1,"ab",2L,"ab");


	@Test
	void testid() {
		auth.setId(123);
		assertEquals(auth.getId(), 123);
	}

	@Test
	void testName() {
		auth.setName("ab");
		assertEquals(auth.getName(), "ab");
	}

	@Test
	void testContactnumber() {
		auth.setContactNumber(2L);
		assertEquals(auth.getContactNumber(),2L);
	}
	

	@Test
	void testEmail() {
		auth.setEmail("ab");
		assertEquals(auth.getEmail(), "ab");
	}

	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(),string);
	}

}
	
	

