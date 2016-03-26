package com.henry.dao;

import java.util.List;
import com.henry.domain.Person;

public interface PersonDao {
	List<Person> findAll();
	Person findById(int id);
	void save(Person person);
	void delete(int id);
}
