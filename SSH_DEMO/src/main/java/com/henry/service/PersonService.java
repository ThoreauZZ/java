package com.henry.service;

import java.util.List;

import com.henry.domain.Person;

public interface PersonService {
	public List<Person> findAll();
	public void save(Person person);
	public void remove(int id);
	public Person find(int id);
}
