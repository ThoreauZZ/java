package com.zhao.esayui.dao;

import java.util.List;
import java.util.Map;

import com.zhao.esayui.domain.User;
import com.zhao.esayui.domain.page.UserPage;

public interface UserDao {
	User getUserByName(String name);
	int saveUser(User user);
	List<User> getUserPages(UserPage userPage);
	int getUserTotalRows();
	int update(String StringStatement ,Object parameter);
}
