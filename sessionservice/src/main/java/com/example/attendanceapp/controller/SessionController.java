package com.example.attendanceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.service.SessionServiceImpl;

@RestController
@CrossOrigin
public class SessionController {

	@Autowired
	SessionServiceImpl sessionServiceImpl;

	@PostMapping("/addsession")
	public void addSession(@RequestHeader("Authorization") String token, @RequestBody Session session) {
		sessionServiceImpl.addSession(token, session);
	}

	@PostMapping("/addsession/{userId}/{sessionId")
	public void addSessionToUser(@RequestHeader("Authorization") String token, @PathVariable int sessionId,
			@PathVariable String userId) {
		sessionServiceImpl.addSessionToUser(token, sessionId, userId);
	}

	@GetMapping("/sessions")
	public List<Session> getSessions(@RequestHeader("Authorization") String token) {
		return sessionServiceImpl.getSessions(token);
	}

	@GetMapping("/sessions/{sessionId}")
	public Session getSessionBySessionId(@RequestHeader("Authorization") String token,
			@PathVariable("sessionId") int sessionId) {
		return sessionServiceImpl.getSessionBySessionId(token, sessionId);
	}
	
	@GetMapping("/sessions/users/{userId}")
	public List<Session> getSessionsByUserId(@RequestHeader("Authorization") String token,
			@PathVariable("userId") String userId) {
		return sessionServiceImpl.getSessionsByUserId(token, userId);
	}

	@PutMapping("/modifysession")
	public void modifySession(@RequestHeader("Authorization") String token, @RequestBody Session session) {
		sessionServiceImpl.modifySession(token, session);
	}

	@DeleteMapping("/deletesession/{sessionId}")
	public void deleteSession(@RequestHeader("Authorization") String token, @PathVariable("sessionId") int sessionId) {
		sessionServiceImpl.deleteSession(token, sessionId);
	}

	@DeleteMapping("/deletesession/{sessionId}/{userId}")
	public void deleteSessionByUser(@RequestHeader("Authorization") String token, @PathVariable("sessionId") int sessionId,
			@PathVariable("userId") int userId) {
		sessionServiceImpl.deleteSessionByUser(token, sessionId,userId);
	}
	
	@PostMapping("/mapskills/{sessionId}/{skillId}")
	public void mapSkills(@RequestHeader("Authorization") String token,@PathVariable("sessionId") int sessionId,
			@PathVariable("skillId") int skillId) {
		sessionServiceImpl.mapSkills(token,sessionId,skillId);
	}
	
	@GetMapping("/getskills/{sessionId}")
	public List<Skill> getSkills(@RequestHeader("Authorization") String token,@PathVariable("sessionId") int sessionId) {
		return sessionServiceImpl.getSkillsBySession(token,sessionId);
	}

}
