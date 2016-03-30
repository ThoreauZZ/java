package com.mvc.henry.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.henry.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/addUser")
	@ResponseBody
	public User addUser(@Valid User user,BindingResult result){
		if(result.hasErrors()){
			log.debug("add Use");
		}
		log.debug("add User --->",user);
		return user;
	}
}
