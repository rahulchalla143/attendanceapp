package com.example.attendanceapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.attendanceapp.model.AuthResponse;
import com.example.attendanceapp.model.UserData;

public interface AuthenticationService extends UserDetailsService{

	AuthResponse login(UserData userlogincredentials);
	AuthResponse getValidity(String token);
	AuthResponse register(UserData userCredentials);
	void approveAdmin(String email, String token);

}
