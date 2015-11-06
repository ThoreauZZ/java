package com.zhao.esayui.web.controller;

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
		System.out.println(userService.getClass().getName());
		ResultEntity result = userService.regist(user);
		return result;
	}
	@RequestMapping("/login.htm")
	@ResponseBody
	public ResultEntity login(User user){
		ResultEntity result = userService.checkLogin(user);
		return result;
	}
}
