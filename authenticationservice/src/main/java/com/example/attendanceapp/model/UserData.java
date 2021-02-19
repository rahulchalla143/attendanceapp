package com.example.attendanceapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "user")
@Table
public class UserData {

	@Id
	@Column(name = "userid", length = 20, unique=true)
	private String userid;
	@Column(name = "upassword", length = 20)
	private String upassword;
	@Column(name = "ufirstname", length = 20)
	private String ufirstname;
	@Column(name = "urole")
	private String urole;
	@Column(name = "ulastname", length = 20)
	private String ulastname;
	@Column(name = "uemail", length = 40, unique=true)
	private String uemail;
	@Column(name = "uage", length = 20)
	private String uage;
	@Column(name = "ugender", length = 20)
	private String ugender;
	@Column(name = "ucontact", length = 20)
	private String ucontact;
	@Column(name = "uapproved", length = 20)
	private String uapproved;

}
