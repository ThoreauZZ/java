package com.zhao.esayui.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhao.esayui.domain.Menu;
import com.zhao.esayui.domain.page.Tree;
import com.zhao.esayui.service.MenuService;

/**
 * 
 * <p>
 * 菜单控制器
 * </p>
 * 
 * @author henry.zhao
 * @version v1.0 2015年11月19日 上午9:10:08
 */
@Controller
@RequestMapping("/menu")
public class MenuContrller {
	@Resource
	private MenuService menuService;

	@RequestMapping("/getMenu")
	@ResponseBody
	public List<Tree> getMenu(Tree tree) {
		/*
		 * 1、看菜单，放入，List
		 */
		Map map = new HashMap();
		map.put("id", tree.getId());
		List<Menu> menus = menuService.queryMenuByPid(map);
		
		List<Tree> trees = new ArrayList<Tree>();// 树
		if (tree.getId() == null) {
			if (menus.size() > 0) {
				for (Menu m : menus) {
					Tree t = new Tree();
					BeanUtils.copyProperties(m, t);
					trees.add(t);
				}
			}
		} else {
//			Menu root = menuService.queryMenuById(map);
//			Tree parent = new Tree();
//			BeanUtils.copyProperties(root, parent);
			List<Tree> childrenTrees = new ArrayList<Tree>();// 树
			if (menus.size() > 0) {
				
				for (Menu m : menus) {
					Tree t = new Tree();
					t.setState("open");
					BeanUtils.copyProperties(m, t);
					childrenTrees.add(t);
				}
			}
//			parent.setChildren(childrenTrees);
//			trees.add(parent);
			return childrenTrees;
		}
		return trees;
	}

}
