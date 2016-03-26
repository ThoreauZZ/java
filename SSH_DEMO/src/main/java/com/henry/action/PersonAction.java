package com.henry.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.henry.domain.Person;
import com.henry.service.PersonService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

@Controller
public class PersonAction  implements Preparable{
		
		@Resource
	 	private PersonService service;
		
	    private List<Person> persons;
	    private Person person;
	    private Integer id;
	    
	    public PersonAction() {
		}


		public PersonAction(PersonService service) {
	        this.service = service;
	    }
	 
	 
	    public String execute() {
	        this.persons = service.findAll();
	        for(Person person:persons){
	        	System.out.println(person.getFirstName());
	        }
	        return Action.SUCCESS;
	    }
	 
	    public String save() {
	        service.save(person);
	        return execute();
	    }
	 
	    public String remove() {
	        service.remove(id);
	        return execute();
	    }
	 
	    public List<Person> getPersons() {
	        return persons;
	    }
	 
	    public Integer getId() {
	        return id;
	    }
	 
	    public void setId(Integer id) {
	        this.id = id;
	    }
	 
	    public void prepare() throws Exception {
	        if (id != null)
	            person = service.find(id);
	    }
	 
	    public Person getPerson() {
	        return person;
	    }
	 
	    public void setPerson(Person person) {
	        this.person = person;
	    }
}