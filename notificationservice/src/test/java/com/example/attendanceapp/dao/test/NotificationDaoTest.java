package com.example.attendanceapp.dao.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.attendanceapp.dao.NotificationDAO;
import com.example.attendanceapp.model.Notification;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NotificationDaoTest {
	
	@Autowired
	NotificationDAO notificationDAO;

	@Test
	@Rollback(false)
	public void testSaveSkill() {
		Notification notification = new Notification(1,"Technical","This is skill DEsc","Yes","124");
		Notification addedNotification =  notificationDAO.save(notification);
		assertNotNull(addedNotification);
	}
	
	@Test
	@Rollback(false)
	public void testFindByNotificationid() {
		Notification notification = new Notification(1,"Technical","This is skill DEsc","Yes","124");
		Notification addedNotification = notificationDAO.save(notification);
		int notificationId = addedNotification.getNotificationid();
		boolean present = notificationDAO.findByNotificationid(notificationId).isPresent();
		assertTrue(present);
	}
	
	@Test
	@Rollback(false)
	public void testFindByUserid() {
		Notification notification1 = new Notification(1,"Technical","This is skill DEsc","Yes","124");
		Notification notification2 = new Notification(2,"Technical","This is skill DEsc","Yes","124");
		List<Notification> notificationList = new ArrayList<Notification>();
		notificationList.add(notification1);
		notificationList.add(notification2);
		assertTrue(notificationDAO.findByUserid("124").size()>=2);
	}
	
	
}
