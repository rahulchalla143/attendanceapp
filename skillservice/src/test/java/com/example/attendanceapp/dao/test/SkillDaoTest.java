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

import com.example.attendanceapp.dao.SkillDAO;
import com.example.attendanceapp.model.Skill;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SkillDaoTest {
	
	@Autowired
	SkillDAO skillDAO;

	@Test
	@Rollback(false)
	public void testSaveSkill() {
		Skill skill = new Skill(1,"Technical","This is skill DEsc");
		Skill addedSkill = skillDAO.save(skill);
		assertNotNull(addedSkill);
	}
	
	@Test
	public void testFindBySkillId() {
		Skill skill = new Skill(1,"Technical","This is skill DEsc");
		Skill addedSkill = skillDAO.save(skill);
		int skillId = addedSkill.getSkillid();
		boolean present = skillDAO.findBySkillid(skillId).isPresent();
		assertTrue(present);
	}
	
	@Test
	@Rollback(false)
	public void testDeleteBySkillId() {
		Skill skill = new Skill(1,"Technical","This is skill DEsc");
		Skill addedSkill = skillDAO.save(skill);
		int skillId = addedSkill.getSkillid();
		skillDAO.deleteBySkillid(skillId);
		boolean present = skillDAO.findBySkillid(skillId).isPresent();
		assertFalse(present);
	}
	
}
