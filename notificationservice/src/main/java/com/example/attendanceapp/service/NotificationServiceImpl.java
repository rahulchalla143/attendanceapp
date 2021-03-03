package com.example.attendanceapp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendanceapp.client.AuthClient;
import com.example.attendanceapp.client.SessionClient;
import com.example.attendanceapp.dao.NotificationDAO;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.Notification;
import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.SessionUserMap;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationDAO notificationDAO;

	@Autowired
	SessionClient sessionClient;

	@Autowired
	AuthClient authClient;

	@Override
	public void markAllAsRead(String token, String userId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("User")) {
			List<Notification> notificationList = notificationDAO.findByUserid(userId);
			for (Notification notification : notificationList) {
				notification.setRead("Yes");
				notificationDAO.save(notification);
			}
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public List<Notification> getAllNotifications(String token, String userId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("User")) {
			LocalDate today = LocalDate.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			List<Session> sessionList = sessionClient.getSessions(token);
			List<Integer> filteredList = sessionList.stream().filter(
					session -> LocalDate.parse(session.getSessiondate(), dateTimeFormatter).plusDays(3).isAfter(today))
					.map(session -> session.getSessionid())
					.collect(Collectors.toList());
			List<SessionUserMap> sessionUserMapList = sessionClient.getSessionUserMapByUserId(token, userId);
			List<SessionUserMap> filteredUserMapList = sessionUserMapList.stream().filter(sessionUserMap -> filteredList.contains(sessionUserMap.getSessionid())).collect(Collectors.toList());
			
			for (SessionUserMap sessionUserMap : filteredUserMapList) {
				Notification notification = new Notification();
				notification.setNotificationtitle("Session Reminder");
				notification.setNotificationdata("You have a session with Session Id : "+sessionUserMap.getSessionid()+"in slot "+sessionUserMap.getSelectedslot()+"in less than 3 days. Mark the calendar!" );
				notification.setRead("No");
				notification.setUserid(userId);
				notificationDAO.save(notification);
			}
			
			return notificationDAO.findByUserid(userId);
			
		} else {
			throw new UnauthorizedException();
		}
	}

}
