package com.techacademy.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {
	@Id
	private String loginUser;
	
	private String password;
	
	private Date validDate;
	
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
}
