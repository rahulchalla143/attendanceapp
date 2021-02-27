package com.example.attendanceapp.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.attendanceapp.controller.SessionController;
import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.model.Skill;
import com.example.attendanceapp.service.SessionServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SessionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	SessionController sessionController;

	@MockBean
	SessionServiceImpl sessionServiceImpl;
	
	public void setup() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(sessionController).build();
	}

	@Test
	public void testGetSessions() throws Exception{
		List<Session> sessionList = new ArrayList<Session>();
		sessionList.add(new Session(1,"ad","ad","ad","ad","ad"));
		sessionList.add(new Session(2,"ad","ad","ad","ad","ad"));
		when(sessionServiceImpl.getSessions("token")).thenReturn(sessionList);
		mockMvc.perform(MockMvcRequestBuilders.get("/sessions").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void testGetSessionBySessionId() throws Exception{
		when(sessionServiceImpl.getSessionBySessionId("token",1)).thenReturn(new Session(1,"ad","ad","ad","ad","ad"));
		mockMvc.perform(MockMvcRequestBuilders.get("/sessions/1").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetSessionsByUserId() throws Exception{
		List<Session> sessionList = new ArrayList<Session>();
		sessionList.add(new Session(1,"ad","ad","ad","ad","ad"));
		sessionList.add(new Session(2,"ad","ad","ad","ad","ad"));
		when(sessionServiceImpl.getSessionsByUserId("token", "125")).thenReturn(sessionList);
		mockMvc.perform(MockMvcRequestBuilders.get("/sessions/users/125").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void testGetSkills() throws Exception{
		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(new Skill(1,"ad","ad"));
		skillList.add(new Skill(2,"ad","ad"));
		when(sessionServiceImpl.getSkillsBySessionId("token", 1)).thenReturn(skillList);
		mockMvc.perform(MockMvcRequestBuilders.get("/getskills/1").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void testGetAllSessionIds() throws Exception{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		when(sessionServiceImpl.getAllSessionIds("token")).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/sessionids").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void testGetApprovedSessions() throws Exception{
		List<Session> sessionList = new ArrayList<Session>();
		sessionList.add(new Session(1,"ad","ad","ad","ad","ad"));
		sessionList.add(new Session(2,"ad","ad","ad","ad","ad"));
		when(sessionServiceImpl.getApprovedSessionsByUserId("token", "125")).thenReturn(sessionList);
		mockMvc.perform(MockMvcRequestBuilders.get("/approvedsessions/125").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void testAddSession() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/addsession").header("Authorization", "token")
				.content("{\"example\":\"example\"}")
	            .contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testAddSessionToUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/addsession/125/1").header("Authorization", "token")
				.content("{\"example\":\"example\"}")
	            .contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testMapSkills() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/mapskills/1/1").header("Authorization", "token")
				.content("{\"example\":\"example\"}")
	            .contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testSetFeedack() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/setfeedback/125/1/This is a good course").header("Authorization", "token")
				.content("{\"example\":\"example\"}")
	            .contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testModifySession() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.put("/modifysession").header("Authorization", "token")
				.content("{\"example\":\"example\"}")
	            .contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDeleteSession() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deletesession/1").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDeleteSessionByUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deletesession/1").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testDeleteSkillsBySessionIdAndSkillId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deletesessionbyskill/1/1").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testHandlerNotFound() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deletesessionbyskillhandlenotfound").header("Authorization", "token"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testHeaderMissing() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deletesessionbyskill/1/1"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}


}
