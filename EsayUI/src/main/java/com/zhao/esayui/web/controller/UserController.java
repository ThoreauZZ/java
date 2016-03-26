package com.zhao.esayui.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhao.esayui.domain.ResultEntity;
import com.zhao.esayui.domain.User;
import com.zhao.esayui.domain.page.UserPage;
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
	@RequestMapping("/updateUser.htm")
	@ResponseBody
	public ResultEntity updateUser(User user){
		ResultEntity result = userService.updateUser(user);
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
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Map getUsers(UserPage userPage){
		Map<String,Object> m = new HashMap<String,Object>();
		return  userService.getUserPages(userPage);
	}
}
