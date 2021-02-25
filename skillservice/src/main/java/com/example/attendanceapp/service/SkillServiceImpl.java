package com.example.attendanceapp.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.client.AuthClient;
import com.example.attendanceapp.dao.SkillDAO;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.model.Skill;

@Service
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	AuthClient authClient;
	
	@Autowired
	SkillDAO skillDAO;

	public void addSkill(String token,Skill skill) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		}
		catch(Exception e) {
			throw new UnauthorizedException();
		}
		if(response.getRole().equals("Admin")) {
			skillDAO.save(skill);
		}
		else {
			throw new UnauthorizedException();
		}
	}

	public List<Skill> getSkills(String token) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		}
		catch(Exception e) {
			throw new UnauthorizedException();
		}
		if(response.getRole().equals("Admin")) {
			return skillDAO.findAll();
		}
		else {
			throw new UnauthorizedException();
		}
	}

	public Skill getSkillById(String token, int skillid) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		}
		catch(Exception e) {
			throw new UnauthorizedException();
		}
		if(response.getRole().equals("Admin")) {
			return skillDAO.findBySkillid(skillid).get();
		}
		else {
			throw new UnauthorizedException();
		}
	}

	@Transactional
	public void deleteSkills(String token, int skillId) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		}
		catch(Exception e) {
			throw new UnauthorizedException();
		}
		if(response.getRole().equals("Admin")) {
			skillDAO.deleteBySkillid(skillId);
		}
		else {
			throw new UnauthorizedException();
		}
	}

	@Override
	public List<Integer> getAllSkillIds(String token) {
		AuthResponse response = authClient.getValidity(token);
		try {
			response.getEmail();
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		if (response.getRole().equals("Admin")) {
			List<Skill> skillList = skillDAO.findAll();
			return skillList.stream()
					.map(skill -> skill.getSkillid())
					.collect(Collectors.toList());
		} else {
			throw new UnauthorizedException();
		}
	}
}
