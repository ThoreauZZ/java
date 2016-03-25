package com.mvc.henry.entity;

public class Zoo {
	private String id;
	private String zooName;
	private String address;
	
	public Zoo() {
		super();
	}
	public Zoo(String id, String zooName, String address) {
		super();
		this.id = id;
		this.zooName = zooName;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZooName() {
		return zooName;
	}
	public void setZooName(String zooName) {
		this.zooName = zooName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Zoo [id=" + id + ", zooName=" + zooName + ", address=" + address + "]";
	}
}
