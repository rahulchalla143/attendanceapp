package com.example.attendanceapp.service;

import java.util.List;

import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.Skill;

public interface SessionService {
	
	//Admin Controls
	public List<Session> getSessions(String token);
	public void addSession(String token, Session session);
	public void modifySession(String token, Session session);
	public void deleteSession(String token, int sessionId);
	public Session getSessionBySessionId(String token, int sessionId);
	public void mapSkills(String token, int sessionId, int skillId);
	public List<Skill> getSkillsBySessionId(String token, int sessionId);
	public void deleteSkillsBySessionIdAndSkillId(String token, int sessionId, int skillId);
	public void approveUserToSession(String token, String userId, int sessionId);
	public List<Integer> getAllSessionIds(String token);
	
	//User Controls
	public List<Session> getSessionsByUserId(String token, String userId);
	public List<Session> getApprovedSessionsByUserId(String token, String userId);
	public void addSessionToUser(String token, int sessionId, String userId,String slot);
	public void deleteSessionByUser(String token, int sessionId, String userId);
	public void markAttendanceByUser(String token, int sessionId, String userId);
	public void addFeedbackByUser(String token, int sessionId, String userId, String feedBack);
	public void rejectUserToSession(String token, String userId, int sessionId);
}
