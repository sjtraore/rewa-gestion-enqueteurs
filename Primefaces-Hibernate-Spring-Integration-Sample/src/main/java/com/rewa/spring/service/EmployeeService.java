package com.rewa.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.hibernate.data.Employee;

@Component
public class EmployeeService {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void register(Employee emp){
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save employee, saving behavior get done in a transactional manner
		session.save(emp);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees(){
		try{
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Employee.class).list();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}