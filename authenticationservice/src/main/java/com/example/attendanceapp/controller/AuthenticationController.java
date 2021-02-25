package com.example.attendanceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.UserData;
import com.example.attendanceapp.service.AuthenticationServiceImpl;

@RestController
@CrossOrigin
public class AuthenticationController {
	
	@Autowired
	AuthenticationServiceImpl authenticationServiceImpl;
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody UserData userCredentials) {
		return authenticationServiceImpl.login(userCredentials);
	}
	
	@GetMapping("/validate")
	public AuthResponse validate(@RequestHeader("Authorization") String token) {
		return authenticationServiceImpl.getValidity(token);
	}
	
	@GetMapping("/approve/{email}")
	public void approveAdmin(@RequestHeader("Authorization") String token,@PathVariable("email") String email) {
		authenticationServiceImpl.approveAdmin(email,token);
	}
	
	@PostMapping("/register")
	public AuthResponse register(@RequestBody UserData userCredentials) {
		return authenticationServiceImpl.register(userCredentials);
	}
	
	@GetMapping("/userids")
	public List<String> getAllUserIds(@RequestHeader("Authorization") String token) {
		return authenticationServiceImpl.getAllUserIds(token);
	}

}
