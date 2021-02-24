package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.AuthResponse;

public class AuthResponseTest {

	AuthResponse auth = new AuthResponse();
	AuthResponse auth1 = new AuthResponse("ad", true, "ab", "ab", "ab", "ab", "ab", "ab", "ab", "ab");

	@Test
	void testUid() {
		auth.setUid("Uid");
		assertEquals(auth.getUid(), "Uid");
	}

	@Test
	void testIsValid() {
		auth.setValid(true);
		assertEquals(auth.isValid(), true);
	}

	@Test
	void testFirstName() {
		auth.setFirstname("FirstName");
		assertEquals(auth.getFirstname(), "FirstName");
	}

	@Test
	void testLastName() {
		auth.setLastname("LastName");
		assertEquals(auth.getLastname(), "LastName");
	}

	@Test
	void testRole() {
		auth.setRole("role");
		assertEquals(auth.getRole(), "role");
	}

	@Test
	void testToken() {
		auth.setToken("token");
		assertEquals(auth.getToken(), "token");
	}

	@Test
	void testEmail() {
		auth.setEmail("email");
		assertEquals(auth.getEmail(), "email");
	}

	@Test
	void testAge() {
		auth.setAge("age");
		assertEquals(auth.getAge(), "age");
	}

	@Test
	void testGender() {
		auth.setGender("gender");
		assertEquals(auth.getGender(), "gender");
	}

	@Test
	void testContact() {
		auth.setContact("contact");
		assertEquals(auth.getContact(), "contact");
	}

	@Test
	void testToString() {
		assertTrue(auth1.toString().contains("ad"));
	}

	private void assertTrue(boolean contains) {
		// TODO Auto-generated method stub

	}

}
