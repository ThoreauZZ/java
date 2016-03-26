package com.henry.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.henry.dao.PersonDao;
import com.henry.domain.Person;
@Repository("personDao")
public class PersonDaoImpl extends HibernateDaoSupport implements PersonDao {
	
	@Resource
	public void setSF(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAll() {
		String hql="from Person";
		return (List<Person>) getHibernateTemplate().find(hql);
	}

	@Override
	public Person findById(int id) {
		return  getHibernateTemplate().get(Person.class, id);
	}

	@Override
	public void save(Person person) {
		System.out.println("插入一条数据");
		getHibernateTemplate().save(person);
		getHibernateTemplate().flush();
		System.out.println("提交一条数据");
	}

	@Override
	public void delete(int id) {
		Person p = new Person();
		p.setId(id);
		getHibernateTemplate().delete(p);
	}

}
