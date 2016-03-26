package com.henry.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.henry.dao.impl.PersonDaoImpl;
import com.henry.domain.Person;

public class TestDao {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		System.out.println(ac);
		
		String[] strs= ac.getBeanDefinitionNames();
		for(String str:strs){
			System.out.println(str);
		}
		PersonDao personDao = ac.getBean("personDao", PersonDaoImpl.class);
		List<Person> persons = personDao.findAll();
		for(Person p:persons){
			System.out.println(p.toString());
		}
		Person p = new Person();
		p.setFirstName("hh");
		p.setLastName("aa");
		personDao.save(p);
	}
}
