package com.example.attendanceapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity(name = "session")
@Table
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sessionid", length = 20, unique=true)
	private int sessionid;
	@Column(name = "sessiondesc", length = 200)
	private String sessiondesc;
	@Column(name = "sessiondate", length = 40)
	private String sessiondate;
	@Column(name = "sessiontime", length = 40)
	private String sessiontime;
	@Column(name = "availableslots", length = 40)
	private String availableslots;
	@Column(name = "feedbackquestion", length = 400)
	private String feedbackquestion;

}
