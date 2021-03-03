package com.example.attendanceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendanceapp.model.Notification;
import com.example.attendanceapp.service.NotificationServiceImpl;

@RestController
@CrossOrigin
public class NotificationController {
	
	@Autowired
	NotificationServiceImpl notificationServiceImpl;
	
	@PostMapping("/markAsRead/{userId}")
	public void login(@RequestHeader("Authorization") String token, @PathVariable("userId") String userId) {
		notificationServiceImpl.markAllAsRead(token, userId);
	}
	
	@GetMapping("/notifications/{userId}")
	public List<Notification> getSkills(@RequestHeader("Authorization") String token, @PathVariable("userId") String userId) {
		return notificationServiceImpl.getAllNotifications(token, userId);
	}
}
