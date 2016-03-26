package com.henry.zhao.service;

import com.henry.zhao.domain.User;

public interface UserService {
	void addUser(User user);
	User getUser(String key);
	void addFile(String key, byte[] bytes);
	byte[] getFile(String key);
}
