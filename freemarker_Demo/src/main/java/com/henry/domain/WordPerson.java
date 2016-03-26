package com.henry.domain;

import java.io.Serializable;

public class WordPerson implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String no;
	private String name;
	private int age;
	private String address;
	
	public WordPerson(String no, String name, int age, String address) {
		super();
		this.no = no;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
