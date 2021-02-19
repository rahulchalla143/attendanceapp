package com.example.attendanceapp.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendanceapp.model.UserData;

public interface UserDAO extends JpaRepository<UserData, String>{
	
	Optional<UserData> findByUemailAndUpassword(String email, String password); 
	Optional<UserData> findByUemail(String email); 

}
