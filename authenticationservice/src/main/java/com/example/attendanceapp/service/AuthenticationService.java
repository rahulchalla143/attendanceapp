package com.example.attendanceapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.UserData;

public interface AuthenticationService extends UserDetailsService{

	AuthResponse login(UserData userlogincredentials);
	AuthResponse getValidity(String token);
	AuthResponse register(UserData userCredentials);
	void approveAdmin(String email, String token);
	public List<String> getAllUserIds(String token);

}
