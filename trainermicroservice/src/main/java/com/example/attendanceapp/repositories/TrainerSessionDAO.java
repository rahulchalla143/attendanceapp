package com.example.attendanceapp.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.attendanceapp.model.SessionTrainerMap;

@Repository
public interface TrainerSessionDAO extends JpaRepository<SessionTrainerMap, Integer>{

	List<SessionTrainerMap> findByTrainerid(int trainerId);
	
	@Modifying
	@Query(value="delete from session_trainer t where t.trainerid=?1 and t.sessionid=?2")
	void deleteByTraineridAndSessionid(int trainerId,int sessionId);

}
