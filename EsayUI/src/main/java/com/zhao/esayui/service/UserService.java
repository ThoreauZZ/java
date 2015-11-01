package com.zhao.esayui.service;
import com.zhao.esayui.domain.ResultEntity;
import com.zhao.esayui.domain.User;

public interface UserService {
	
	public User getUserByName(String id);
	ResultEntity checkLogin(String username,String password);
	ResultEntity regist(User user);
}
