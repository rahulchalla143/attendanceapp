package com.example.attendanceapp.service.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

//	@Test
//	public void testApproveUserToSession() {
//		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
//		when(sessionUserDAO.findByUseridAndSessionid("1", 1)).thenReturn(new SessionUserMap(1,1,"1","a","A","a","A"));
//	}
	
	@Test
	public void testGetApprovedSessionsByUserId() {
		List<SessionUserMap> sessionUserMapList =new ArrayList<SessionUserMap>();
		sessionUserMapList.add(new SessionUserMap(1,1,"1","a","a","Yes","a"));
		when(sessionUserDAO.findByUserid("1")).thenReturn(sessionUserMapList);
		when(sessionDAO.findBySessionid(1)).thenReturn(Optional.of(new Session(1,"a","a","a","a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertEquals(1, sessionServiceImpl.getSessionsByUserId("token", "1").get(0).getSessionid());
		
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
	

}
