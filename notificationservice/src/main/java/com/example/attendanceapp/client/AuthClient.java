package com.example.attendanceapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.attendanceapp.model.AuthResponse;

@FeignClient(url="${auth.feign.client}", name="${auth.feign.name}")
public interface AuthClient {

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public AuthResponse getValidity(@RequestHeader("Authorization") final String token);
	
}