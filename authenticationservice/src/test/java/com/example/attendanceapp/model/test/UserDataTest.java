package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.UserData;

public class UserDataTest {
	
	UserData auth = new UserData();
	UserData auth1 = new UserData("ad","ab","ab","ab","ab","ab","ab","ab","ab","ab");

	@Test
	void testUid() {
		auth.setUserid("Uid");
		assertEquals(auth.getUserid(), "Uid");
	}
	
	
	@Test
	void testFirstName() {
		auth.setUfirstname("FirstName");
		assertEquals(auth.getUfirstname(), "FirstName");
	}
	

	@Test
	void testLastName() {
		auth.setUlastname("LastName");
		assertEquals(auth.getUlastname(), "LastName");
	}
	
	@Test
	void testRole() {
		auth.setUrole("role");
		assertEquals(auth.getUrole(), "role");
	}
	
	@Test
	void testPassword() {
		auth.setUpassword("token");
		assertEquals(auth.getUpassword(), "token");
	}
	
	@Test
	void testEmail() {
		auth.setUemail("email");
		assertEquals(auth.getUemail(), "email");
	}
	
	@Test
	void testAge() {
		auth.setUage("age");
		assertEquals(auth.getUage(), "age");
	}
	
	@Test
	void testGender() {
		auth.setUgender("gender");
		assertEquals(auth.getUgender(), "gender");
	}
	
	@Test
	void testContact() {
		auth.setUcontact("contact");
		assertEquals(auth.getUcontact(), "contact");
	}
	
			
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(),string);
	}


	

	
}





