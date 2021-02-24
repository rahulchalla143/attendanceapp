package com.example.attendanceapp.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.client.AuthClient;
import com.example.attendanceapp.client.SkillClient;
import com.example.attendanceapp.dao.SessionDAO;
import com.example.attendanceapp.dao.SessionSkillDAO;
import com.example.attendanceapp.dao.SessionUserDAO;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.SessionSkillMap;
import com.example.attendanceapp.model.SessionUserMap;
import com.example.attendanceapp.model.Skill;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	AuthClient authClient;

	@Autowired
	SkillClient skillClient;

	@Autowired
	SessionDAO sessionDAO;

	@Autowired
	SessionSkillDAO sessionSkillDAO;
	
	@Autowired
	SessionUserDAO sessionUserDAO;

	public void addSession(String token, Session session) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			sessionDAO.save(session);
		} else {
			throw new UnauthorizedException();
		}
	}

	public void addSessionToUser(String token, int sessionId, String userId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			SessionUserMap sessionUserMap = new SessionUserMap();
			sessionUserMap.setSessionid(sessionId);
			sessionUserMap.setUserid(userId);
			sessionUserDAO.save(sessionUserMap);
		} else {
			throw new UnauthorizedException();
		}
	}

	public List<Session> getSessions(String token) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			return sessionDAO.findAll();
		} else {
			throw new UnauthorizedException();
		}
	}

	public List<Session> getSessionsByUserId(String token, String userId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			List<SessionUserMap> sessionUserMapList =  sessionUserDAO.findByUserid(userId);
			return sessionUserMapList.stream().map(sessionUserMap->getSessionBySessionId(token, sessionUserMap.getSessionid())).collect(Collectors.toList());
		} else {
			throw new UnauthorizedException();
		}
	}

	@Transactional
	public void modifySession(String token, Session session) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			int sessionId = session.getSessionid();
			Session sessionToModify = sessionDAO.findBySessionid(sessionId).get();
			sessionToModify.setSessionid(sessionId);
			sessionToModify.setSessiondesc(session.getSessiondesc());
			sessionToModify.setSessiondate(session.getSessiondate());
			sessionToModify.setSessiontime(session.getSessiontime());
			sessionDAO.save(sessionToModify);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Transactional
	public void deleteSession(String token, int sessionId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			sessionDAO.deleteBySessionid(sessionId);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Transactional
	public void deleteSessionByUser(String token, int sessionId, String userId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			sessionUserDAO.deleteByUseridAndSessionid(userId, sessionId);
		} else {
			throw new UnauthorizedException();
		}
	}

	public void mapSkills(String token, int sessionId, int skillId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			SessionSkillMap sessionSkillMap = new SessionSkillMap();
			sessionSkillMap.setSessionid(sessionId);
			sessionSkillMap.setSkillid(skillId);
			sessionSkillDAO.save(sessionSkillMap);
		} else {
			throw new UnauthorizedException();
		}
	}

	public List<Skill> getSkillsBySession(String token, int sessionId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		}
		catch(Exception e) {
			throw new UnauthorizedException();
		}
		if(response.getRole().equals("Admin")) {
			List<SessionSkillMap> sessionSkillMapList = sessionSkillDAO.findAllBySessionid(sessionId);
			List<Skill> skillList = sessionSkillMapList.stream().map(sessionSkill -> skillClient.getSkillById(token,sessionSkill.getSkillid())).collect(Collectors.toList());
			return skillList;
		}
		else {
			throw new UnauthorizedException();
		}
	}

	public Session getSessionBySessionId(String token, int sessionId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			return sessionDAO.findBySessionid(sessionId).get();
		} else {
			throw new UnauthorizedException();
		}
	}
}
