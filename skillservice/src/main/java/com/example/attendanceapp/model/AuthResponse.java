package com.example.attendanceapp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String uid;
	private boolean isValid;
	private String firstname;
	private String token;
	private String role;
	private String lastname;
	private String email;
	private String age;
	private String gender;
	private String contact;
}
