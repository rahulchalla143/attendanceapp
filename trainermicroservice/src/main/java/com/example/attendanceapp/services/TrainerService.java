package com.example.attendanceapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendanceapp.clients.AuthClient;
import com.example.attendanceapp.exceptions.TrainerAlreadyExistsException;
import com.example.attendanceapp.exceptions.TrainerNotFoundException;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.Trainer;
import com.example.attendanceapp.repositories.TrainerRepository;

@Service
public class TrainerService {

	@Autowired
	TrainerRepository trainerRepository;
	
	@Autowired
	AuthClient authClient;
	
	@Transactional
	public List<Trainer> getTrainerListAdmin(String token) throws TrainerNotFoundException{
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			return trainerRepository.findAll();
		} else {
			throw new UnauthorizedException();
		}
	}

	public Trainer getTrainer(String token,Integer trainer_id) throws TrainerAlreadyExistsException, TrainerNotFoundException {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			Optional<Trainer> trainer=trainerRepository.findById(trainer_id);
			if(trainer.isPresent())
				return trainer.get();
			else {				
				throw new TrainerNotFoundException();
			}
		} else {
			throw new UnauthorizedException();
		}
	}
	
	
	@Transactional
	public void modifyTrainer(String token,Trainer trainer,Integer id) throws TrainerAlreadyExistsException {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			Trainer t=trainerRepository.findById(id).get();
			t.setName(trainer.getName());
			t.setEmail(trainer.getEmail());
			t.setContactNumber(trainer.getContactNumber());
			t.setSession_id(trainer.getSession_id());
			t.setSkill_id(trainer.getSkill_id());
			trainerRepository.save(t);
		} else {
			throw new UnauthorizedException();
		}
	}

	
	@Transactional
	public void removeTrainer(int userId,String token) throws TrainerAlreadyExistsException {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			trainerRepository.removeTrainerDetails(userId);;
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
			trainerRepository.save(trainer);
		} else {
			throw new UnauthorizedException();
		}
	}


}
