package com.example.attendanceapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.model.Trainer;
import com.example.attendanceapp.services.TrainerServiceImpl;

@CrossOrigin
@RestController
public class TrainerController {
	@Autowired
	private TrainerServiceImpl trainerService;
	
	@GetMapping("/trainers")
	public List<Trainer> getAdminItems(@RequestHeader("Authorization") String token){
		return trainerService.getTrainerList(token);
	}
	
	@GetMapping("/trainers/{trainer_id}")
	public Trainer getTrainer(@RequestHeader("Authorization") String token,@PathVariable Integer trainer_id){
		return trainerService.getTrainer(token,trainer_id);
	}
	
	@PutMapping("/modify/{id}")
	public void modifyTrainer(@RequestHeader("Authorization") String token,@RequestBody Trainer trainer,@PathVariable("id") Integer id){
		trainerService.modifyTrainer(token,trainer,id);
	}
	
	@PostMapping("/addTrainer")
	public void addTrainer(@RequestHeader("Authorization") String token,@RequestBody Trainer trainer){
		trainerService.addTrainer(token,trainer);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTrainer(@PathVariable(name="id") final int userId, @RequestHeader("Authorization") String token){
		trainerService.removeTrainer(userId,token);
	}
	
	@PostMapping("/mapSkills/{trainerId}/{skillId}")
	public void mapSkills(@RequestHeader("Authorization") String token,@PathVariable("trainerId") int trainerId,@PathVariable("skillId") int skillId){
		trainerService.mapSkills(token, trainerId, skillId);
	}

	@GetMapping("/getSkills/{trainer_id}")
	public List<Skill> getSkillsByTrainerId(@RequestHeader("Authorization") String token,@PathVariable Integer trainerId){
		return trainerService.getSkillsByTrainerId(token, trainerId);
	}
	
	@DeleteMapping("deleteSkills/{trainerId}/{skillId}")
	public void deleteSkillsByTrainerIdAndSkillId(@RequestHeader("Authorization") String token,@PathVariable("trainerId") int trainerId,@PathVariable("skillId") int skillId){
		trainerService.deleteSkillsByTrainerIdAndSkillId(token, trainerId, skillId);
	}
	
	@GetMapping("/getAllTrainerIds")
	public List<Integer> getAllTrainerIds(@RequestHeader("Authorization") String token){
		return trainerService.getAllTrainerIds(token);
	}
	
	@PostMapping("/mapSessionToTrainer/{trainerId}/{sessionId}")
	public void mapSessionToTrainer(@RequestHeader("Authorization") String token,@PathVariable("trainerId") int trainerId,@PathVariable("sessionId") int sessionId){
		trainerService.mapSessionToTrainer(token, trainerId, sessionId);
	}
	
	@GetMapping("/getSessions/{trainerId}")
	public List<Session> getSessionsByTrainerId(@RequestHeader("Authorization") String token,@PathVariable("trainerId") int trainerId){
		return trainerService.getSessionsByTrainerId(token, trainerId);
	}
	
	@DeleteMapping("deleteSkills/{trainerId}/{sessionId}")
	public void deleteTrainerFromSession(@RequestHeader("Authorization") String token,@PathVariable("trainerId") int trainerId,@PathVariable("sessionId") int sessionId){
		trainerService.deleteTrainerFromSession(token, trainerId, sessionId);
	}

	
}


