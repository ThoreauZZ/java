package com.zhao.esayui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhao.esayui.domain.User;
import com.zhao.esayui.persistence.UserMapper;
import com.zhao.esayui.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private UserMapper userMapper;

	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}
	
}
