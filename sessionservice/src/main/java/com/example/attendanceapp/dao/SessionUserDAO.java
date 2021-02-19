package com.example.attendanceapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.attendanceapp.model.SessionUserMap;


public interface SessionUserDAO extends JpaRepository<SessionUserMap, Integer>{

	List<SessionUserMap> findByUserid(String userId);
	
	@Modifying
	@Query(value="delete from session_user s where s.userid=?1 and s.sessionid=?2")
	void deleteByUseridAndSessionid(int userId, int sessionId);

}
