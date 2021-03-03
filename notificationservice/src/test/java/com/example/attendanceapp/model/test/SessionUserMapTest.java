package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.SessionUserMap;

public class SessionUserMapTest {
	
	SessionUserMap auth = new SessionUserMap();
	SessionUserMap auth1 = new SessionUserMap(1,2,"ab","ab","Yes","ab","ab");
	
	

	@Test
	void testSessionid() {
		auth.setSessionid(123);
		assertEquals(auth.getSessionid(), 123);
	}

	@Test
	void testSessionuserid() {
		auth.setSessionuserid(2);
		assertEquals(auth.getSessionuserid(), 2);
	}

	@Test
	void testUserid() {
		auth.setUserid("ab");
		assertEquals(auth.getUserid(),"ab");
	}
	
	@Test
	void testSelectedSlot() {
		auth.setSelectedslot("selectedslot");
		assertEquals(auth.getSelectedslot(),"selectedslot");
	}

	@Test
	void testAttended() {
		auth.setAttended("attended");
		assertEquals(auth.getAttended(),"attended");
	}

	@Test
	void testFeedback() {
		auth.setFeedback("feedback");
		assertEquals(auth.getFeedback(),"feedback");
	}
	
	@Test
	void testApproved() {
		auth.setApproved("approved");
		assertEquals(auth.getApproved(),"approved");
	}
	
	
	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(),string);
	}

}
	
	
	
	
	
	
	


