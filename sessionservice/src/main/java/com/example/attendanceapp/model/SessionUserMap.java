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
@Entity(name = "session_user")
@Table
public class SessionUserMap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "session_user_id", length = 20, unique=true)
	private int sessionuserid;
	@Column(name = "sessionid", length = 40)
	private int sessionid;
	@Column(name = "userid", length = 40)
	private String userid;
	@Column(name = "selectedslot", length = 40)
	private String selectedslot;
	@Column(name = "attended", length = 40)
	private String attended;
	@Column(name = "approved", length = 40)
	private String approved;
	@Column(name = "feedback", length = 40)
	private String feedback;

}
