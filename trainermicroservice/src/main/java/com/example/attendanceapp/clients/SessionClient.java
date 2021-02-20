package com.example.attendanceapp.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.attendanceapp.model.Session;


@FeignClient(url = "${session.feng.client}", name = "${session.feng.name}")
public interface SessionClient {
	@GetMapping("/sessions/{sessionId}")
	public Session getSessionBySessionId(@RequestHeader("Authorization") String token,@PathVariable("sessionId") int sessionId);
	
	@PostMapping("/addsession")
	public void addSession(@RequestHeader("Authorization") String token, @RequestBody Session session);
}