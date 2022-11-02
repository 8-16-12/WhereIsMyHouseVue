package com.ssafy.dto;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String pass;
	private String name;
	private String birth;
	private String email;
	private String addr;
	private String addr2;
	
	public User() {}

	public User(String id, String pass, String name, String birth, String email, String addr, String addr2) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.addr = addr;
		this.addr2 = addr2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", name=" + name + ", birth=" + birth + ", email=" + email
				+ ", addr=" + addr + ", addr2=" + addr2 + "]";
	}
	
	
}
