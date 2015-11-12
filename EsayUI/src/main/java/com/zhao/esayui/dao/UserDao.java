package com.zhao.esayui.dao;

import java.util.List;
import java.util.Map;

import com.zhao.esayui.domain.User;

public interface UserDao {
	User getUserByName(String name);
	int saveUser(User user);
	List<User> getUserPages(Map map);
	int getUserTotalRows();
}
