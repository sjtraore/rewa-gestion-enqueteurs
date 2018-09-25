package com.rewa.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.hibernate.data.Person;;

@Component
@Transactional
public class PersonService {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void register(Person person) {
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save employee, saving behavior get done in a transactional manner
		session.save(person);
	}

	@SuppressWarnings("unchecked")
	public List<Person> getPersons() {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			List<Person> result = session.createCriteria(Person.class).list();
			System.out.println("getPersons: " + ((result != null) ? result.size() : 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
