package com.zhao.esayui.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhao.esayui.dao.BaseDao;
import com.zhao.esayui.dao.UserDao;
import com.zhao.esayui.domain.User;
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
	public List<User> getUserPages(Map map) {
		return getSqlSessionTemplate().selectList("getUserPages", map);
	}

	@Override
	public int getUserTotalRows() {
		return getSqlSessionTemplate().selectOne("getUserTotalRows", null);
	}

}
