package com.example.attendanceapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendanceapp.model.SessionSkillMap;


public interface SessionSkillDAO extends JpaRepository<SessionSkillMap, Integer>{
	
	List<SessionSkillMap> findAllBySessionid(int sessionId);

}
