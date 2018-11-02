package com.rewa.spring.service;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Person;

@Component
@Transactional
public class LoginService {
	private static final Logger log = Logger.getLogger(LoginService.class);
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Person validate(String user, String password) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.add(Restrictions.and(Restrictions.eq(Constant.USERNAME, user), Restrictions.eq(Constant.PASSWORD, password)));
			Person result = (Person) criteria.uniqueResult();

			if (result != null) {
				//result found, means valid inputs
				log.debug("Credentials found for user: " + user);
				return result;
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
