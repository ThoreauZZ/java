package com.zhao.esayui.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhao.esayui.dao.UserDao;
import com.zhao.esayui.dao.impl.UserDaoImpl;
import com.zhao.esayui.domain.User;


public class TestApplication {
	/*
	 * 使用logback,但这儿引入的包是slf4j。
	 */
	private static Logger log = LoggerFactory.getLogger(TestApplication.class);
	public static void main(String[] args) {
		
		String conf = "applicationContext-new.xml";
		// 实例化Spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		log.info("实例化spring容器",ac);
		/*
		 * 有时候bean注入错误，找不到对应bean，可以使用该方法列出spring的所有bean。
		 */
		String[] strs = ac.getBeanDefinitionNames();
		for(String str:strs){
			System.out.println(str);
		}
		UserDao userDao = 
				ac.getBean("userDao",UserDaoImpl.class);
		User user = userDao.getUserByName("zz");
		System.out.println("id:"+user.getId()+" 姓名："+" 密码："+user.getUsername()+user.getPassword());
		
	}
}
