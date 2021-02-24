package com.example.attendanceapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.attendanceapp.model.SessionSkillMap;


public interface SessionSkillDAO extends JpaRepository<SessionSkillMap, Integer>{
	
	List<SessionSkillMap> findAllBySessionid(int sessionId);
	
	@Modifying
	@Query(value="delete from session_skill s where s.sessionid=?1 and s.skillid=?2")
	void deleteBySessionidAndSkillid(int sessionId,int skillId);

}
