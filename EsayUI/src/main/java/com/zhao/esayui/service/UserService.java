package com.zhao.esayui.service;
import com.zhao.esayui.domain.ResultEntity;
import com.zhao.esayui.domain.User;

public interface UserService {
	
	public User getUserByName(String id);
	ResultEntity checkLogin(User user);
	ResultEntity regist(User user);
}
