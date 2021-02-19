package com.example.attendanceapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.attendanceapp.model.Skill;

public interface SkillDAO extends JpaRepository<Skill, Integer>{
	
	Optional<Skill> findBySkillid(int skillid);

	@Modifying
	@Query(value="delete from skill s where s.skillid=?1")
	void deleteBySkillid(int skillId);

}
