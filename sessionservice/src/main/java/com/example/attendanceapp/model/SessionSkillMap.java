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
@Entity(name = "session_skill")
@Table
public class SessionSkillMap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "session_skill_id", length = 20, unique=true)
	private int sessionskillid;
	@Column(name = "sessionid", length = 40)
	private int sessionid;
	@Column(name = "skillid", length = 40)
	private int skillid;

}
