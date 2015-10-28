package com.zhao.esayui.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhao.esayui.persistence.UserMapper;

public class TestApplication {
	public static void main(String[] args) {
		String conf = "applicationContext.xml";
		// 实例化Spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		System.out.println(ac);
		
		SqlSessionFactory sf = ac.getBean(
				"sqlSessionFactory",SqlSessionFactory.class);
		System.out.println(sf);
		
		UserMapper userDao = 
				ac.getBean("userMapper",UserMapper.class);
		userDao.getUserByID(1);
	}
}
