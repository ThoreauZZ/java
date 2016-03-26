package com.henry.domain;
 
import java.io.Serializable;
 
public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String location;
	
	public Person(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
 
}