package com.zhao.esayui.persistence;

import java.util.List;
import java.util.Map;

import com.zhao.esayui.domain.User;

public interface UserMapper {
	User getUserByName(String name);
	void saveUser(User user);
	List<User> getUserPages(Map map);
	int getUserTotalRows();
}
