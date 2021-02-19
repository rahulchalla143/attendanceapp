package com.example.attendanceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Specified User Not Found")
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
