package com.example.attendanceapp.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.attendanceapp.model.Session;

public interface SessionDAO extends JpaRepository<Session, Integer>{
	
	@Modifying
	@Query(value="delete from session s where s.sessionid=?1")
	void deleteBySessionid(int sessionId);

	Optional<Session> findBySessionid(int sessionId);
	
}
