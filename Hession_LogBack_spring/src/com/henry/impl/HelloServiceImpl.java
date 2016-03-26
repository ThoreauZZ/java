package com.henry.impl;



import org.apache.commons.logging.LogFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.henry.HelloService;

public class HelloServiceImpl implements HelloService {
	private static Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		logger.info("---to ---sayHello-----");
		return "hello hession service";
	}

}
