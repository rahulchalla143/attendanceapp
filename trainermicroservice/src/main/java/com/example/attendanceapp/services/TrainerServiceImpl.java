package com.example.attendanceapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendanceapp.clients.AuthClient;
import com.example.attendanceapp.clients.SessionClient;
import com.example.attendanceapp.clients.SkillClient;
import com.example.attendanceapp.exceptions.TrainerNotFoundException;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.SessionTrainerMap;
import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.model.Trainer;
import com.example.attendanceapp.model.TrainerSkillMap;
import com.example.attendanceapp.repositories.TrainerDAO;
import com.example.attendanceapp.repositories.TrainerSessionDAO;
import com.example.attendanceapp.repositories.TrainerSkillDAO;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	TrainerDAO trainerDAO;

	@Autowired
	TrainerSessionDAO trainerSessionDAO;

	@Autowired
	TrainerSkillDAO trainerSkillDAO;

	@Autowired
	AuthClient authClient;

	@Autowired
	SkillClient skillClient;
	
	@Autowired
	SessionClient sessionClient;

	@Transactional
	public List<Trainer> getTrainerList(String token) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin") || response.getRole().equals("User")) {
			return trainerDAO.findAll();
		} else {
			throw new UnauthorizedException();
		}
	}

	public Trainer getTrainer(String token, Integer trainer_id) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin") || response.getRole().equals("User")) {
			Optional<Trainer> trainer = trainerDAO.findById(trainer_id);
			if (trainer.isPresent())
				return trainer.get();
			else {
				throw new TrainerNotFoundException();
			}
		} else {
			throw new UnauthorizedException();
		}
	}

	@Transactional
	public void modifyTrainer(String token, Trainer trainer, Integer id) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			Trainer t = trainerDAO.findById(id).get();
			t.setName(trainer.getName());
			t.setEmail(trainer.getEmail());
			t.setContactNumber(trainer.getContactNumber());
			trainerDAO.save(t);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Transactional
	public void removeTrainer(int userId, String token) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			trainerDAO.removeTrainerDetails(userId);
			;
		} else {
			throw new UnauthorizedException();
		}
	}

	public void addTrainer(String token, Trainer trainer) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			trainerDAO.save(trainer);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public void mapSkills(String token, int trainerId, int skillId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			TrainerSkillMap trainerSkillMap = new TrainerSkillMap();
			trainerSkillMap.setSkillid(skillId);
			trainerSkillMap.setTrainerid(trainerId);
			trainerSkillDAO.save(trainerSkillMap);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public List<Skill> getSkillsByTrainerId(String token, int trainerId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			List<TrainerSkillMap> trainerSkillMapList = trainerSkillDAO.findByTrainerid(trainerId);
			return trainerSkillMapList.stream()
					.map(trainerSkillMap -> skillClient.getSkillById(token, trainerSkillMap.getSkillid()))
					.collect(Collectors.toList());
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public void deleteSkillsByTrainerIdAndSkillId(String token, int trainerId, int skillId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			trainerSkillDAO.deleteByTraineridAndSkillid(trainerId, skillId);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public List<Integer> getAllTrainerIds(String token) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			List<Trainer> trainerList = trainerDAO.findAll();
			return trainerList.stream()
					.map(trainer -> trainer.getId())
					.collect(Collectors.toList());
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public void mapSessionToTrainer(String token, int trainerId, int sessionId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			SessionTrainerMap sessionTrainerMap = new SessionTrainerMap();
			sessionTrainerMap.setSessionid(sessionId);
			sessionTrainerMap.setTrainerid(trainerId);
			trainerSessionDAO.save(sessionTrainerMap);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public List<Session> getSessionsByTrainerId(String token, int trainerId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			List<SessionTrainerMap> sessionTrainerMapList = trainerSessionDAO.findByTrainerid(trainerId);
			return sessionTrainerMapList.stream()
					.map(sessionTrainerMap -> sessionClient.getSessionBySessionId(token, sessionTrainerMap.getSessionid()))
					.collect(Collectors.toList());
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public void deleteTrainerFromSession(String token, int trainerId, int sessionId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			trainerSessionDAO.deleteByTraineridAndSessionid(trainerId, sessionId);
		} else {
			throw new UnauthorizedException();
		}

	}

}
