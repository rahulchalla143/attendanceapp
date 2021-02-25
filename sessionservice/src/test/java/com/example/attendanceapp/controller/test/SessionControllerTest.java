package com.example.attendanceapp.controller.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.attendanceapp.model.Session;
import com.example.attendanceapp.service.SessionServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SessionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	SessionServiceImpl sessionServiceImpl;

//	@Test
//	public void testAddSession() throws Exception{
//		Session session = new Session(1,"ad","ad","ad","ad","ad");
//		mockMvc.perform(MockMvcRequestBuilders.post("/addsession").contentType(session.toString()).header("Authorization", "token"))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//	}

}
