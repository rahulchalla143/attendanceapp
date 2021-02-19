package com.example.attendanceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SkillserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillserviceApplication.class, args);
	}

}
