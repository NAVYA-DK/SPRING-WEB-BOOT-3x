package com.kuebiko.dto;

public class SignupDTO {
	
	private int sid;
	private String name;
	private String email;
	private String gender;
	private String passportFlag;
	private String photo;
	private int hid;
	
	
	
	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPassportFlag() {
		return passportFlag;
	}

	public void setPassportFlag(String passportFlag) {
		this.passportFlag = passportFlag;
	}

	public SignupDTO() {
		
	}
	
	public SignupDTO(int sid, String name, String email, String gender) {
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