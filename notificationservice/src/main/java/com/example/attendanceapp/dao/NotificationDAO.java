package com.example.attendanceapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendanceapp.model.Notification;

public interface NotificationDAO extends JpaRepository<Notification, Integer>{
	
	Optional<Notification> findByNotificationid(int notificationId);
	
	List<Notification> findByUserid(String userId);

}
