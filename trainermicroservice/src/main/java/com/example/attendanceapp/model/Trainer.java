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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="trainer")
public class Trainer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="trainer_id")
	private Integer id;
	
	@Column(name="trainer_name")
	private String Name;
	
	@Column(name="trainer_contactNumber")
	private Long contactNumber;
	
	@Column(name="trainer_email") 
	private String email;

}
