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
import com.example.attendanceapp.model.SessionUserMap;
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

	@PostMapping("/addsession/{userId}/{sessionId}/{slot}")
	public void addSessionToUser(@RequestHeader("Authorization") String token, @PathVariable int sessionId,
			@PathVariable String userId,@PathVariable String slot) {
		sessionServiceImpl.addSessionToUser(token, sessionId, userId, slot);
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
	
	@GetMapping("/sessions/usermap/{userId}")
	public List<SessionUserMap> getSessionUserMapByUserId(@RequestHeader("Authorization") String token,
			@PathVariable("userId") String userId) {
		return sessionServiceImpl.getSessionUserMapByUserId(token, userId);
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
			@PathVariable("userId") String userId) {
		sessionServiceImpl.deleteSessionByUser(token, sessionId,userId);
	}
	
	@PostMapping("/mapskills/{sessionId}/{skillId}")
	public void mapSkills(@RequestHeader("Authorization") String token,@PathVariable("sessionId") int sessionId,
			@PathVariable("skillId") int skillId) {
		sessionServiceImpl.mapSkills(token,sessionId,skillId);
	}
	
	@GetMapping("/getskills/{sessionId}")
	public List<Skill> getSkills(@RequestHeader("Authorization") String token,@PathVariable("sessionId") int sessionId) {
		return sessionServiceImpl.getSkillsBySessionId(token,sessionId);
	}
	
	@DeleteMapping("/deletesessionbyskill/{sessionId}/{skillId}")
	public void deleteSkillsBySessionIdAndSkillId(@RequestHeader("Authorization") String token, @PathVariable("sessionId") int sessionId,
			@PathVariable("skillId") int skillId) {
		sessionServiceImpl.deleteSkillsBySessionIdAndSkillId(token, sessionId,skillId);
	}
	
	@GetMapping("/approvesession/{userId}/{sessionId}")
	public void approveUser(@RequestHeader("Authorization") String token,@PathVariable("userId") String userId,@PathVariable("sessionId") int sessionId) {
		sessionServiceImpl.approveUserToSession(token,userId,sessionId);
	}
	
	@GetMapping("/rejectsession/{userId}/{sessionId}")
	public void rejectUser(@RequestHeader("Authorization") String token,@PathVariable("userId") String userId,@PathVariable("sessionId") int sessionId) {
		sessionServiceImpl.rejectUserToSession(token,userId,sessionId);
	}
	
	@GetMapping("/sessionids")
	public List<Integer> getAllSessionIds(@RequestHeader("Authorization") String token) {
		return sessionServiceImpl.getAllSessionIds(token);
	}
	
	@GetMapping("/approvedsessions/{userId}")
	public List<Session> getApprovedSessions(@RequestHeader("Authorization") String token,@PathVariable("userId") String userId) {
		return sessionServiceImpl.getApprovedSessionsByUserId(token, userId);
	}
	
	@GetMapping("/markattendance/{userId}/{sessionId}")
	public void markAttendance(@RequestHeader("Authorization") String token,@PathVariable("userId") String userId,@PathVariable("sessionId") int sessionId) {
		sessionServiceImpl.markAttendanceByUser(token, sessionId, userId);
	}
	
	@PostMapping("/setfeedback/{userId}/{sessionId}/{feedback}")
	public void setFeedback(@RequestHeader("Authorization") String token,@PathVariable("userId") String userId,@PathVariable("sessionId") int sessionId,@PathVariable("feedback") String feedback) {
		sessionServiceImpl.addFeedbackByUser(token, sessionId, userId, feedback);
	}


}
