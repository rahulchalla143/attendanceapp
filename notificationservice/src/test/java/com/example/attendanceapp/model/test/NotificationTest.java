package com.example.attendanceapp.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.attendanceapp.model.Notification;

public class NotificationTest {
	
	Notification auth = new Notification();
	Notification auth1 = new Notification(1,"ab","ab","Yes","123");

	@Test
	void testNotificationId() {
		auth.setNotificationid(123);
		assertEquals(auth.getNotificationid(), 123);
	}

	@Test
	void testNotificationTitle() {
		auth.setNotificationtitle("title");
		assertEquals(auth.getNotificationtitle(), "title");
	}

	@Test
	void testNotificationData() {
		auth.setNotificationdata("ab");
		assertEquals(auth.getNotificationdata(),"ab");
	}
	
	@Test
	void testMarkRead() {
		auth.setRead("ab");
		assertEquals(auth.getRead(),"ab");
	}
	
	@Test
	void testUserId() {
		auth.setUserid("ab");
		assertEquals(auth.getUserid(),"ab");
	}
	
	@Test
	void testToString() {
		String string = auth1.toString();
		assertEquals(auth1.toString(),string);
	}

}
	
	
	
	
	
	
	


	
	



	
	