package com.example.attendanceapp.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.SessionUserMap;


@FeignClient(url = "${session.feign.client}", name = "${session.feign.name}")
public interface SessionClient {
	
	@GetMapping("/sessions")
	public List<Session> getSessions(@RequestHeader("Authorization") String token);
	
	@GetMapping("/sessions/usermap/{userId}")
	public List<SessionUserMap> getSessionUserMapByUserId(@RequestHeader("Authorization") String token,
			@PathVariable("userId") String userId);
}