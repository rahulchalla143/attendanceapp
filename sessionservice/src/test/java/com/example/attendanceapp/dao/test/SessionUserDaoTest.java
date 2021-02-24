package com.example.attendanceapp.dao.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.attendanceapp.dao.SessionDAO;
import com.example.attendanceapp.dao.SessionSkillDAO;
import com.example.attendanceapp.dao.SessionUserDAO;
import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.SessionSkillMap;
import com.example.attendanceapp.model.SessionUserMap;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SessionUserDaoTest {
	
	@Autowired
	SessionUserDAO sessionUserDAO;

	@Test
	@Rollback(false)
	public void testSaveSessionUser() {
		SessionUserMap sessionUserMap = new SessionUserMap(1,1,"125","1","Yes","Good One!");
		SessionUserMap addedSessionUserMap = sessionUserDAO.save(sessionUserMap);
		assertNotNull(addedSessionUserMap);
	}
	
	@Test
	public void testFindByUserId() {
		assertThat(sessionUserDAO.findByUserid("125")).size().isGreaterThan(0);
	}
	
	@Test
	public void testDeleteByUseridAndSessionid() {
		SessionUserMap sessionUserMap = new SessionUserMap(2,1,"125","1","Yes","Good One!");
		SessionUserMap addedSessionUserMap = sessionUserDAO.save(sessionUserMap);
		int sessionId = addedSessionUserMap.getSessionid();
		String userId = addedSessionUserMap.getUserid();
		sessionUserDAO.deleteByUseridAndSessionid(userId, sessionId);
		assertThat(sessionUserDAO.findByUserid(userId)).isNullOrEmpty();
	}
	
}
