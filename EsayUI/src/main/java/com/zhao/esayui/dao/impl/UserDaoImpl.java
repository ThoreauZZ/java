package com.zhao.esayui.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhao.esayui.dao.BaseDao;
import com.zhao.esayui.dao.UserDao;
import com.zhao.esayui.domain.User;
import com.zhao.esayui.domain.page.UserPage;
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User getUserByName(String name) {
		return getSqlSessionTemplate().selectOne("getUserByName", name);
	}

	@Override
	public int saveUser(User user) {
		return getSqlSessionTemplate().insert("saveUser", user);
	}

	@Override
	public List<User> getUserPages(UserPage userPage) {
		return getSqlSessionTemplate().selectList("getUserPages", userPage);
	}

	@Override
	public int getUserTotalRows() {
		return getSqlSessionTemplate().selectOne("getUserTotalRows", null);
	}

	@Override
	public int update(String StringStatement, Object parameter) {
		return getSqlSessionTemplate().update(StringStatement, parameter);
	}

}
