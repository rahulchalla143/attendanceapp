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
@Entity(name = "notificationtable")
@Table
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notificationid", length = 20, unique=true)
	private int notificationid;
	@Column(name = "notificationtitle", length = 40)
	private String notificationtitle;
	@Column(name = "notificationdata", length = 80)
	private String notificationdata;
	@Column(name = "userid", length = 40)
	private String userid;
	@Column(name = "isread", length = 20)
	private String read;
	

}

