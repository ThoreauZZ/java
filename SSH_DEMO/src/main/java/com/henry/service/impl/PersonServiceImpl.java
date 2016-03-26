package com.henry.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.henry.dao.impl.PersonDaoImpl;
import com.henry.domain.Person;
import com.henry.service.PersonService;
/**
 * @PersistenceContext will make Spring inject an EntityManager 
 * into the service when it is instantiated. 
 * @PersistenceContext annotation can be placed on the field, or on the setter method. 
 * If the class is annotated as @Transactional, Spring will make sure that its methods run inside a transaction.
 * 
 * @author henry.zhao
 *
 */
@Service("service")
public class PersonServiceImpl implements PersonService {
	@Resource
	private PersonDaoImpl personDao;

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAll() {
		Query query = getEntityManager().createQuery("select p FROM Person p");
		return query.getResultList();
	}

	@Override
	public void save(Person person) {
		if (person.getId() == null) {
			// new
//			em.persist(person);
			personDao.save(person);
		} else {
			// update
			em.merge(person);
		}
	}

	@Override
	public void remove(int id) {
		Person person = find(id);
        if (person != null) {
            em.remove(person);
        }
	}

	@Override
	public Person find(int id) {
		return em.find(Person.class, id);
	}

}
