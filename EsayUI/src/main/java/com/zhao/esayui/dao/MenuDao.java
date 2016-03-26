package com.zhao.esayui.dao;

import java.util.List;

import com.zhao.esayui.domain.Menu;

public interface MenuDao {
	List<Menu> queryForList(String statment,Object param);
	Menu queryForObject(String statment,Object param);
}
