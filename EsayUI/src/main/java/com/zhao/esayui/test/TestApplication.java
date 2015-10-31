package com.zhao.esayui.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhao.esayui.domain.User;
import com.zhao.esayui.persistence.UserMapper;

public class TestApplication {
	public static void main(String[] args) {
		
		String conf = "applicationContext.xml";
		// 实例化Spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		System.out.println(ac);
		
//		SqlSessionFactory sf = ac.getBean(
//				"sqlSessionFactory",SqlSessionFactory.class);
//		System.out.println(sf);
		String[] strs = ac.getBeanDefinitionNames();
		for(String str:strs){
			System.out.println(str);
		}
		UserMapper userDao = 
				ac.getBean("userMapper",UserMapper.class);
		User user = userDao.getUserByName("赵州");
		System.out.println(user.getId()+user.getUsername()+user.getPassword());
		
	}
}
