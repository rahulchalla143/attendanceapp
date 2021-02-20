package com.example.attendanceapp.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.attendanceapp.model.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{

	
	@Modifying
	@Query(value="delete from Trainer c where c.id=?1 ")
	void removeTrainerDetails(int userId);
}
