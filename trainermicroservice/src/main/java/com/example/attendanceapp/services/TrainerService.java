package com.example.attendanceapp.services;

import java.util.List;

import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.model.Trainer;

public interface TrainerService {
	
	//Admin Controls
	
	public List<Trainer> getTrainerList(String token);
	public Trainer getTrainer(String token,Integer trainer_id);
	public void modifyTrainer(String token,Trainer trainer,Integer id);
	public void removeTrainer(int userId,String token);
	public void addTrainer(String token, Trainer trainer);
	public void mapSkills(String token, int trainerId, int skillId);
	public List<Skill> getSkillsByTrainerId(String token, int trainerId);
	public void deleteSkillsByTrainerIdAndSkillId(String token, int trainerId, int skillId);
	public List<Integer> getAllTrainerIds(String token);
	
	//SessionControls
	
	public void mapSessionToTrainer(String token,int trainerId, int sessionId);
	public List<Session> getSessionsByTrainerId(String token, int trainerId);
	public void deleteTrainerFromSession(String token, int trainerId, int sessionId);

}
