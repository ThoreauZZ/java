package com.zhao.esayui.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.zhao.esayui.service.UserService;
import com.zhao.esayui.service.impl.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	public static void main(String[] args) {
	}
}
