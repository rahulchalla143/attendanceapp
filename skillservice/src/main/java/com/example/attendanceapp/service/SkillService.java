package com.example.attendanceapp.service;

import java.util.List;

import com.example.attendanceapp.model.Skill;

public interface SkillService {
	
	public void addSkill(String token,Skill skill);
	public List<Skill> getSkills(String token);
	public Skill getSkillById(String token, int skillid);
	public void deleteSkills(String token, int skillId);

}
