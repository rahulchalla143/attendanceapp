package com.example.attendanceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "SuperUser Had Not Yet Approved")
public class NotYetApprovedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
