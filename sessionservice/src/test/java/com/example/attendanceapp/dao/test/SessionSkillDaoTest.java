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
import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.SessionSkillMap;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SessionSkillDaoTest {
	
	@Autowired
	SessionSkillDAO sessionSkillDAO;

	@Test
	@Rollback(false)
	public void testSaveSessionSkill() {
		SessionSkillMap sessionSkillMap = new SessionSkillMap(1,20,1);
		SessionSkillMap addedSessionSkillMap = sessionSkillDAO.save(sessionSkillMap);
		assertNotNull(addedSessionSkillMap);
	}
	
	@Test
	public void testFindBySessionId() {
		assertThat(sessionSkillDAO.findAllBySessionid(20)).size().isGreaterThan(0);
	}
	
	@Test
	public void testDeleteBySessionIdAndSkillId() {
		SessionSkillMap sessionSkillMap = new SessionSkillMap(1,2,3);
		SessionSkillMap addedSessionSkillMap = sessionSkillDAO.save(sessionSkillMap);
		int sessionId = addedSessionSkillMap.getSessionid();
		int skillId = addedSessionSkillMap.getSkillid();
		sessionSkillDAO.deleteBySessionidAndSkillid(sessionId, skillId);
		assertThat(sessionSkillDAO.findAllBySessionid(sessionId)).isNullOrEmpty();
	}
	
}
