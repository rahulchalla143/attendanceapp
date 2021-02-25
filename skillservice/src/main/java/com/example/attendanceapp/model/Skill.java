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
@Entity(name = "skill")
@Table
public class Skill {

	@Id
	@Column(name = "skillid", length = 20, unique=true)
	private int skillid;
	@Column(name = "skilltype", length = 20)
	private String skilltype;
	@Column(name = "skilldesc", length = 200)
	private String skilldesc;

}
