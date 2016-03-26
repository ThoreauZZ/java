package com.zhao.esayui.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhao.esayui.dao.BaseDao;
import com.zhao.esayui.dao.MenuDao;
import com.zhao.esayui.domain.Menu;
@Repository(value = "menuDao")
public class MenuDaoImpl extends BaseDao implements MenuDao  {

	@Override
	public List<Menu> queryForList(String statment, Object param) {
		return getSqlSessionTemplate().selectList(statment, param);
	}

	@Override
	public Menu queryForObject(String statment, Object param) {
		return getSqlSessionTemplate().selectOne(statment, param);
	}

}
