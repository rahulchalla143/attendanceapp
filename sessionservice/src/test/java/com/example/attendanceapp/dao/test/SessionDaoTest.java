package com.example.attendanceapp.dao.test;

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
import com.example.attendanceapp.model.Session;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SessionDaoTest {
	
	@Autowired
	SessionDAO sessionDAO;

	@Test
	@Rollback(false)
	public void testSaveSession() {
		Session session = new Session(1,"This is a session1","2015-05-12","20:45","1,2,3","How did you feel about this?");
		Session addedSession = sessionDAO.save(session);
		assertNotNull(addedSession);
	}
	
	@Test
	public void findBySessionId() {
		boolean present = sessionDAO.findBySessionid(20).isPresent();
		assertTrue(present);
	}
	
	@Test
	@Rollback(false)
	public void testDeleteBySessionId() {
		Session session = new Session(1,"This is a session1","2015-05-12","20:45","1,2,3","How did you feel about this?");
		Session addedSession = sessionDAO.save(session);
		int sessionId = addedSession.getSessionid();
		sessionDAO.deleteBySessionid(sessionId);
		boolean present = sessionDAO.findBySessionid(sessionId).isPresent();
		assertFalse(present);
	}
	
}
