package com.henry.zhao.storm.entity;

import java.io.Serializable;

public class LogBean implements Serializable{
	private static final long serialVersionUID = 6536577329450972410L;
	private String level;
	private String logger;
	private String timestamp;
	private String message;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLogger() {
		return logger;
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
