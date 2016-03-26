package com.zhao.esayui.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhao.esayui.dao.MenuDao;
import com.zhao.esayui.domain.Menu;
import com.zhao.esayui.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	@Override
	public List<Menu> queryMenuByPid(Map m) {
		return menuDao.queryForList("sql_queryMenuByPid", m);
	}
	@Override
	public Menu queryMenuById(Map map) {
		return menuDao.queryForObject("sql_queryMenuById", map);
	}

}
