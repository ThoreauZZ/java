package com.mvc.henry.entity;

import javax.validation.constraints.NotNull;

public class User {
	
	@NotNull(message="username cannot null")
	private String username;
	
	@NotNull
	private String password;
	
	private String addres;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
}
