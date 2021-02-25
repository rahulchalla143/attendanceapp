package com.example.attendanceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.service.SkillServiceImpl;

@RestController
@CrossOrigin
public class SkillController {
	
	@Autowired
	SkillServiceImpl skillServiceImpl;
	
	@PostMapping("/addskill")
	public void login(@RequestHeader("Authorization") String token, @RequestBody Skill skill) {
		skillServiceImpl.addSkill(token,skill);
	}
	
	@GetMapping("/skills")
	public List<Skill> getSkills(@RequestHeader("Authorization") String token) {
		return skillServiceImpl.getSkills(token);
	}
	
	@DeleteMapping("/deleteskills/{skillId}")
	public void deleteSkills(@RequestHeader("Authorization") String token,@PathVariable("skillId") int skillId) {
		skillServiceImpl.deleteSkills(token,skillId);
	}

	@GetMapping("/skills/{skillid}")
	public Skill getSkillById(@RequestHeader("Authorization") String token,@PathVariable("skillid") int skillid) {
		return skillServiceImpl.getSkillById(token, skillid);
	}
	
	@GetMapping("/skillids")
	public List<Integer> getAllSkillIds(@RequestHeader("Authorization") String token) {
		return skillServiceImpl.getAllSkillIds(token);
	}
	
}
