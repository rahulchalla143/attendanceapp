package com.example.attendanceapp.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.attendanceapp.model.TrainerSkillMap;

@Repository
public interface TrainerSkillDAO extends JpaRepository<TrainerSkillMap, Integer>{
	
	List<TrainerSkillMap> findByTrainerid(int trainerId);
	
	@Modifying
	@Query(value="delete from trainer_skill t where t.trainerid=?1 and t.skillid=?2")
	void deleteByTraineridAndSkillid(int trainerId,int skillId);
	
}
