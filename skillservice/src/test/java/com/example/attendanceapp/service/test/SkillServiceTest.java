package com.example.attendanceapp.service.test;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.attendanceapp.client.AuthClient;
import com.example.attendanceapp.dao.SkillDAO;
import com.example.attendanceapp.exceptions.UnauthorizedException;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.service.SkillServiceImpl;

@SpringBootTest
public class SkillServiceTest {
	
	@InjectMocks
	SkillServiceImpl skillServiceImpl;
	
	@Mock
	AuthClient authClient;
	
	@Mock
	SkillDAO skillDAO;
	
	@Test
	public void testGetSkills() {
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(new Skill(1,"As","As"));
		skillList.add(new Skill(2,"As","As"));
		when(skillDAO.findAll()).thenReturn(skillList);
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertEquals(2, skillServiceImpl.getSkills("token").size());
	}
	
	@Test
	public void testGetSkillById() {
		when(skillDAO.findBySkillid(1)).thenReturn(Optional.of(new Skill(1,"as","As")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertEquals(1, skillServiceImpl.getSkillById("token",1).getSkillid());
	}
	
	@Test
	public void testGetSkillIds() {
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(new Skill(1,"As","As"));
		skillList.add(new Skill(2,"As","As"));
		when(skillDAO.findAll()).thenReturn(skillList);
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertEquals(2, skillServiceImpl.getAllSkillIds("token").size());
	}
	
	@Test
	public void testAddSkill() {
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->skillServiceImpl.addSkill("token", new Skill(1,"a","a")));	
	}
	
	@Test
	public void testdeleteSkill() {
		when(skillDAO.findBySkillid(1)).thenReturn(Optional.of(new Skill(1,"a","a")));
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","Admin","w","w","w","w","w"));
		assertDoesNotThrow(()->skillServiceImpl.deleteSkills("token",1));	
	}
	
	@Test
	public void testUnauthorizedException() {
		when(authClient.getValidity("token")).thenReturn(new AuthResponse("a",true,"w","w","User","w","w","w","w","w"));
		assertThrows(UnauthorizedException.class,()->skillServiceImpl.addSkill("token", new Skill(1,"a","a")));	
	}
	
}
