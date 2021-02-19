package com.example.attendanceapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.attendanceapp.model.Skill;

@FeignClient(url="${skill.feign.client}", name="${skill.feign.name}")
public interface SkillClient {

	@GetMapping("/skills/{skillid}")
	public Skill getSkillById(@RequestHeader("Authorization") String token,@PathVariable("skillid") int skillid);
	
}