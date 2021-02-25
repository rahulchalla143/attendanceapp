package com.example.attendanceapp.dao.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.attendanceapp.model.Trainer;
import com.example.attendanceapp.repositories.TrainerDAO;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TrainerDAOTest {
	
	@Autowired
	private TrainerDAO repo;
	
	@Test
	public void testCreateTrainer() {
		Trainer trainer=new Trainer();
		trainer.setContactNumber((long)1872337);
		trainer.setEmail("abc@gmail.com");
		trainer.setId(123);
		trainer.setName("Sachin");
		Trainer savedTrainer=repo.save(trainer);
		assertNotNull(savedTrainer);
		
	}
	
	@Test
	public void testFindById() {
		int id=1;
		Optional<Trainer> trainer=repo.findById(id);
		assertThat(trainer.get().getId()).isEqualTo(id);
	}
	
	@Test
	public void testFindAll() {
		List<Trainer> list=repo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	public void testRemoveTrainerDetails() {
		int id=1;
		boolean beforeDeletion=repo.findById(id).isPresent();
		repo.deleteById(id);
		boolean afterDeletion=repo.findById(id).isPresent();
		
		assertTrue(beforeDeletion);
		assertFalse(afterDeletion);
	}
}
