package com.example.attendanceapp.service;

import java.util.ArrayList;
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
		if (response.getRole().equals("User")) {
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
		if (response.getRole().equals("Admin") || response.getRole().equals("User")) {
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
		if (response.getRole().equals("User")) {
			List<SessionUserMap> sessionUserMapList = sessionUserDAO.findByUserid(userId);
			return sessionUserMapList.stream()
					.map(sessionUserMap -> getSessionBySessionId(token, sessionUserMap.getSessionid()))
					.collect(Collectors.toList());
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
			sessionToModify.setAvailableslots(session.getAvailableslots());
			sessionToModify.setFeedbackquestion(session.getFeedbackquestion());
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
		if (response.getRole().equals("User")) {
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

	public List<Skill> getSkillsBySessionId(String token, int sessionId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin") || response.getRole().equals("User")) {
			List<SessionSkillMap> sessionSkillMapList = sessionSkillDAO.findAllBySessionid(sessionId);
			List<Skill> skillList = sessionSkillMapList.stream()
					.map(sessionSkill -> skillClient.getSkillById(token, sessionSkill.getSkillid()))
					.collect(Collectors.toList());
			return skillList;
		} else {
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
		if (response.getRole().equals("Admin") || response.getRole().equals("User")) {
			return sessionDAO.findBySessionid(sessionId).get();
		} else {
			throw new UnauthorizedException();
		}
	}

	@Transactional
	@Override
	public void deleteSkillsBySessionIdAndSkillId(String token, int sessionId, int skillId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			sessionSkillDAO.deleteBySessionidAndSkillid(sessionId, skillId);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public void approveUserToSession(String token, String userId, int sessionId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			SessionUserMap sessionUserMap = sessionUserDAO.findByUseridAndSessionid(userId, sessionId);
			sessionUserMap.setApproved("Yes");
			sessionUserDAO.save(sessionUserMap);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public List<Session> getApprovedSessionsByUserId(String token, String userId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("User")) {
			List<Session> sessionList = new ArrayList<Session>();
			List<SessionUserMap> sessionUserMapList = sessionUserDAO.findByUserid(userId);
			List<SessionUserMap> approvedSessionUserMapList = sessionUserMapList.stream()
					.filter(sessionUserMap -> sessionUserMap.getApproved().equals("Yes")).collect(Collectors.toList());
			for (SessionUserMap sessionUserMap2 : approvedSessionUserMapList) {
				sessionList.add(sessionDAO.findBySessionid(sessionUserMap2.getSessionid()).get());
			}
			return sessionList;
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public void markAttendanceByUser(String token, int sessionId, String userId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("User")) {
			SessionUserMap sessionUserMap = sessionUserDAO.findByUseridAndSessionid(userId, sessionId);
			sessionUserMap.setAttended("Yes");
			sessionUserDAO.save(sessionUserMap);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public void addFeedbackByUser(String token, int sessionId, String userId, String feedBack) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("User")) {
			SessionUserMap sessionUserMap = sessionUserDAO.findByUseridAndSessionid(userId, sessionId);
			sessionUserMap.setFeedback(feedBack);
			sessionUserDAO.save(sessionUserMap);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public List<Integer> getAllSessionIds(String token) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			return sessionDAO.findAll().stream().map(session->session.getSessionid()).collect(Collectors.toList());
		} else {
			throw new UnauthorizedException();
		}
	}
}
