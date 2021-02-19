package com.example.attendanceapp.service;

import java.util.List;

import com.example.attendanceapp.model.Session;

public interface SessionService {
	
	public void addSession(String token, Session session);
	public void addSessionToUser(String token, int sessionId, String userId);
	public List<Session> getSessions(String token);
	public List<Session> getSessionsByUserId(String token, String userId);
	public void modifySession(String token, Session session);
	public void deleteSession(String token, int sessionId);
	public void deleteSessionByUser(String token, int sessionId, int userId);
	public void mapSkills(String token, int sessionId, int skillId);
	public Session getSessionBySessionId(String token, int sessionId);

}
