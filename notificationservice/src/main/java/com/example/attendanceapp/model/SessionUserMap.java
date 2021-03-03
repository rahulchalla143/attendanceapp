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
public class SessionUserMap {

	private int sessionuserid;
	private int sessionid;
	private String userid;
	private String selectedslot;
	private String attended;
	private String approved;
	private String feedback;

}
