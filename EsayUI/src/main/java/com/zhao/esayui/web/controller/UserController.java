package com.zhao.esayui.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhao.esayui.domain.ResultEntity;
import com.zhao.esayui.domain.User;
import com.zhao.esayui.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/regist.htm")
	@ResponseBody
	public ResultEntity regist(User user){
		ResultEntity result = userService.regist(user);
		return result;
	}
	@RequestMapping("/login.htm")
	@ResponseBody
	public ResultEntity login(User user){
		ResultEntity result = userService.checkLogin(user);
		return result;
	}
	@RequestMapping("/getUsers.htm")
	@ResponseBody
	public Map getUsers(int page,int rows){
		Map<String,Integer> m = new HashMap<String,Integer>();
		m.put("pageSize", rows);
		m.put("pageIndex", (page-1)*rows);
		return  userService.getUserPages(m);
	}
}
