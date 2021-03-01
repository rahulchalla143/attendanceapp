package com.example.attendanceapp.service.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.attendanceapp.client.AuthClient;
import com.example.attendanceapp.client.SkillClient;
import com.example.attendanceapp.dao.SessionDAO;
import com.example.attendanceapp.dao.SessionSkillDAO;
import com.example.attendanceapp.dao.SessionUserDAO;
import com.example.attendanceapp.exceptions.SessionNotFoundException;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.SessionSkillMap;
import com.example.attendanceapp.model.SessionUserMap;
import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.service.SessionServiceImpl;

@SpringBootTest
public class SessionServiceTest {
	
	@InjectMocks
	SessionServiceImpl sessionServiceImpl;
	
	@Mock
	SessionDAO sessionDAO;

	@Mock
	SessionUserDAO sessionUserDAO;

	@Mock
	SessionSkillDAO sessionSkillDAO;
	
	@Mock
	AuthClient authClient;
	
	@Mock
	SkillClient skillClient;
	
	@Test
	public void testGetSessions() {
		List<Session> sessionList = new ArrayList<Session>();
		sessionList.add(new Session(1,"a","a","a","a","a"));
		sessionList.add(new Session(2,"a","a","a","a","a"));
		when(sessionDAO.findAll()).thenReturn(sessionList);
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertEquals(2, sessionServiceImpl.getSessions("token").size());
		
	}

	
	@Test
	public void testGetSessionsByUserId() {
		List<SessionUserMap> sessionUserMapList =new ArrayList<SessionUserMap>();
		sessionUserMapList.add(new SessionUserMap(1,1,"1","a","a","a","a"));
		when(sessionUserDAO.findByUserid("1")).thenReturn(sessionUserMapList);
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertEquals(1, sessionServiceImpl.getSessionsByUserId("token", "1").get(0).getSessionid());
		
	}
	
	@Test
	public void testGetSkillsBySessionId() {
		List<SessionSkillMap> sessionSkillMapList =new ArrayList<SessionSkillMap>();
		sessionSkillMapList.add(new SessionSkillMap(1,1,1));
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(sessionSkillDAO.findAllBySessionid(1)).thenReturn(sessionSkillMapList);
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		when(skillClient.getSkillById("token", 1)).thenReturn(new Skill(1,"s","s"));
		assertEquals(1, sessionServiceImpl.getSkillsBySessionId("token", 1).get(0).getSkillid());
	}
	
	@Test
	public void testGetSessionBySessionId() {
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		assertEquals(1, sessionServiceImpl.getSessionBySessionId("token", 1).getSessionid());
	}

	@Test
	public void testMarkAttendanceByUser() {
		when(sessionUserDAO.findByUseridAndSessionid("a", 1)).thenReturn(new SessionUserMap(1,1,"a","a","a","a","a"));
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.markAttendanceByUser("token", 1, "a"));
	}
	
	@Test
	public void testGetFeedbackFromUser() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(sessionUserDAO.findByUseridAndSessionid("a", 1)).thenReturn(new SessionUserMap(1,1,"1","a","a","a","a"));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("1",true,"w","w","User","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.addFeedbackByUser("token", 1, "a", "This is soo good!"));
	}
	
	@Test
	public void testApproveUserToSession() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(sessionUserDAO.findByUseridAndSessionid("a", 1)).thenReturn(new SessionUserMap(1,1,"a","a","a","a","a"));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.approveUserToSession("token", "a", 1));
	}
	
	@Test
	public void testRejectUserToSession() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(sessionUserDAO.findByUseridAndSessionid("a", 1)).thenReturn(new SessionUserMap(1,1,"a","a","a","a","a"));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.rejectUserToSession("token", "a", 1));
	}
	
	@Test
	public void testGetApprovedSessionsByUserId() {
		List<SessionUserMap> sessionUserMapList =new ArrayList<SessionUserMap>();
		sessionUserMapList.add(new SessionUserMap(1,1,"1","a","a","Yes","a"));
		when(sessionUserDAO.findByUserid("1")).thenReturn(sessionUserMapList);
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertEquals(1, sessionServiceImpl.getApprovedSessionsByUserId("token", "1").get(0).getSessionid());
		
	}
	
	@Test
	public void testGetSessionIds() {
		List<Session> sessionList = new ArrayList<Session>();
		sessionList.add(new Session(1,"a","a","a","a","a"));
		sessionList.add(new Session(2,"a","a","a","a","a"));
		when(sessionDAO.findAll()).thenReturn(sessionList);
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertEquals(2, sessionServiceImpl.getAllSessionIds("token").size());
		
	}
	
	@Test
	public void testAddSession() {
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.addSession("token", new Session(1,"a","a","a","a","a")));	
	}
	
	@Test
	public void testAddSessionToUser() {
		when(sessionUserDAO.findByUseridAndSessionid("a", 1)).thenReturn(new SessionUserMap(1,1,"a","a","a","a","a"));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.addSessionToUser("token", 1, "a","w"));	
	}
	
	@Test
	public void testModifySession() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.modifySession("token", new Session(1,"a","a","a","a","a")));	
	}
	
	@Test
	public void testDeleteSession() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.deleteSession("token",1));	
	}
	
	@Test
	public void testDeleteSessionByUser() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.deleteSessionByUser("token",1,"a"));	
	}
	
	@Test
	public void testMapSkills() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.mapSkills("token", 1, 1));	
	}
	
	@Test
	public void testDeleteSkillsBySessionIdAndSkillId() {
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->sessionServiceImpl.deleteSkillsBySessionIdAndSkillId("token", 1, 1));	
	}
	
	
	@Test
	public void testSessionNotFoundException() {
		List<SessionSkillMap> sessionSkillMapList =new ArrayList<SessionSkillMap>();
		sessionSkillMapList.add(new SessionSkillMap(1,1,1));
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertThrows(SessionNotFoundException.class, ()->sessionServiceImpl.getSkillsBySessionId("token", 2));
	}
	

}
