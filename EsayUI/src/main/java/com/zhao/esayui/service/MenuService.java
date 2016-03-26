package com.zhao.esayui.service;

import java.util.List;
import java.util.Map;

import com.zhao.esayui.domain.Menu;

public interface MenuService {
	List<Menu> queryMenuByPid(Map map);
	Menu queryMenuById(Map map);
}
