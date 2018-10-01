package com.rewa.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.beans.PersonBean;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Status;
import com.rewa.utils.PersonUtils;;

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
	public void save(Person person) {
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save employee, saving behavior get done in a transactional manner
		if(person.getIdPerson() == 0) {
			session.save(person);
		} else {
			session.merge(person);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Person> getPersons() {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			List<Person> result = session.createCriteria(Person.class).list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<PersonBean> getAllAgents() {
		List<PersonBean> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Person> persons = session.createCriteria(Person.class).list();
			// System.out.println("getAllAgents: " + ((persons != null) ? persons.size() :
			// 0));
			if (persons != null) {
				result = new ArrayList<PersonBean>();
				for (Person person : persons) {
					result.add(PersonUtils.getPersonBeanByPerson(person));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<PersonBean> getAgentsByStatus(Status status) {
		List<PersonBean> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Person.class)
					.add(Restrictions.eq("status", status));
			@SuppressWarnings("unchecked")
			List<Person> persons = criteria.list();
			if (persons != null) {
				result = new ArrayList<PersonBean>();
				for (Person person : persons) {
					result.add(PersonUtils.getPersonBeanByPerson(person));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
