package com.example.attendanceapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendanceapp.exceptions.TrainerAlreadyExistsException;
import com.example.attendanceapp.exceptions.TrainerNotFoundException;
import com.example.attendanceapp.model.Trainer;
import com.example.attendanceapp.services.TrainerService;

@RestController
public class TrainerController {
	@Autowired
	private TrainerService trainerService;
	
	@GetMapping("/trainers")
	public List<Trainer> getAdminItems(@RequestHeader("Authorization") String token) throws TrainerNotFoundException{
		return trainerService.getTrainerListAdmin(token);
	}
	
	@GetMapping("/trainers/{trainer_id}")
	public Trainer getTrainer(@RequestHeader("Authorization") String token,@PathVariable Integer trainer_id) throws  TrainerAlreadyExistsException, TrainerNotFoundException {
		return trainerService.getTrainer(token,trainer_id);
	}
	
	@PutMapping("/modify/{id}")
	public void modifyTrainer(@RequestHeader("Authorization") String token,@RequestBody Trainer trainer,@PathVariable("id") Integer id) throws TrainerAlreadyExistsException {
		trainerService.modifyTrainer(token,trainer,id);
	}
	
	@PostMapping("/addTrainer")
	public void addTrainer(@RequestHeader("Authorization") String token,@RequestBody Trainer trainer) throws TrainerNotFoundException, TrainerAlreadyExistsException {
		trainerService.addTrainer(token,trainer);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTrainer(@PathVariable(name="id") final int userId, @RequestHeader("Authorization") String token) throws TrainerAlreadyExistsException {
		trainerService.removeTrainer(userId,token);
	}

}


