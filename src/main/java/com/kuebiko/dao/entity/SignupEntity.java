package com.kuebiko.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//class which is used to map with database 
//table is called entity
@Entity
@Table(name="asignup_tbl")
public class SignupEntity {
	
	@Id //means it is primary key
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sid;
	private String name;
	private String email;
	private String gender;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "signupEntity")
	private List<LoginHistoryEntity> history=new ArrayList<LoginHistoryEntity>();
	
	
	public List<LoginHistoryEntity> getHistory() {
		return history;
	}

	public void setHistory(List<LoginHistoryEntity> history) {
		this.history = history;
	}

	public SignupEntity() {
		
	}
	
	public SignupEntity(int sid, String name, String email, String gender) {
		this.sid = sid;
		this.name = name;
		this.email = email;
		this.gender = gender;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}