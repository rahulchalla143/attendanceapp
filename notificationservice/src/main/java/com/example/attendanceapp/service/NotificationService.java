package com.example.attendanceapp.service;

import java.util.List;

import com.example.attendanceapp.model.Notification;



public interface NotificationService {
	
	public void markAllAsRead(String token, String userId);
	public List<Notification> getAllNotifications(String token, String userId);
}
