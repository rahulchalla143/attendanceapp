package com.example.attendanceapp.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.attendanceapp.controller.SkillController;
import com.example.attendanceapp.service.SkillServiceImpl;
import com.example.attendanceapp.model.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SkillControllerTest{

    @Autowired
    private MockMvc mockMvc;
    
    @InjectMocks
    SkillController skillController;

    @MockBean
    SkillServiceImpl skillServiceImpl;
    
    public void setup() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(skillController).build();
	}
    
    @Test
	public void testGetSkills() throws Exception{
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(new Skill(1,"ad","ad"));
		skillList.add(new Skill(2,"ad","ad"));
		when(skillServiceImpl.getSkills("token")).thenReturn(skillList);
		mockMvc.perform(MockMvcRequestBuilders.get("/skills").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}

	@Test
	public void testGetAllSkillIds() throws Exception{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		when(skillServiceImpl.getAllSkillIds("token")).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/skillids").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void testAddSkill() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/addskill").header("Authorization", "token")
				.content("{\"example\":\"example\"}")
	            .contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
    
	@Test
	public void testDeleteSkill() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteskills/1").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetSkillById() throws Exception{
		when(skillServiceImpl.getSkillById("token",1)).thenReturn(new Skill(1,"ad","ad"));
		mockMvc.perform(MockMvcRequestBuilders.get("/skills/1").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
  
}
