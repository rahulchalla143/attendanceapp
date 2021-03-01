package com.example.attendanceapp.controller.test;

import static org.mockito.Mockito.when;

import java.util.Optional;

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

import com.example.attendanceapp.controller.AuthenticationController;
import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.UserData;
import com.example.attendanceapp.service.AuthenticationServiceImpl;



@ExtendWith(SpringExtension.class)
@WebMvcTest
public class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
		
	@InjectMocks
	AuthenticationController authController;

	@MockBean
	 AuthenticationServiceImpl authServiceImpl;
	
	@Test
	void loginTest() throws Exception
	{	
		
		
		 UserData user1 = new UserData("ab","ab", "ab","ab","ab", "ab", "ab","ab","ab", "ab");
		 Optional<UserData> data = Optional.of(user1);
		 AuthResponse auth = new AuthResponse("ab", true, "ab","ab","ab", "ab", "ab","ab","ab", "ab"); 

		when(authServiceImpl.login(user1)).thenReturn(auth);
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"example\":\"example\"}")
				.header("Authorization", "token"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
		

	@Test
	void getValidityTest() throws Exception{
		 UserData user1 = new UserData("ab","ab", "ab","ab","ab", "ab", "ab","ab","ab", "ab");
		 Optional<UserData> data = Optional.of(user1);
		 AuthResponse auth = new AuthResponse("ab", true, "ab","ab","ab", "ab", "ab","ab","ab", "ab"); 

		when(authServiceImpl.getValidity("token")).thenReturn(auth);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/validate").header("Authorization", "token"))
		.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	void registertest() throws Exception {
		UserData user1 = new UserData("ab","ab", "ab","ab","ab", "ab", "ab","ab","ab", "ab");
		 Optional<UserData> data = Optional.of(user1);
		 AuthResponse auth = new AuthResponse("ab", true, "ab","ab","ab", "ab", "ab","ab","ab", "ab"); 

		when(authServiceImpl.register(user1)).thenReturn(auth);
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"example\":\"example\"}")
				.header("Authorization", "token"))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void approveAdmintest() throws Exception {
		UserData user1 = new UserData("ab","ab", "ab","ab","ab", "ab", "ab","ab","ab", "ab");
		 Optional<UserData> data = Optional.of(user1);
		 AuthResponse auth = new AuthResponse("ab", true, "ab","ab","ab", "ab", "ab","ab","ab", "ab"); 
		 
		 
		 mockMvc.perform(MockMvcRequestBuilders.get("/approve/ab").header("Authorization", "token"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	
	}
		
}

