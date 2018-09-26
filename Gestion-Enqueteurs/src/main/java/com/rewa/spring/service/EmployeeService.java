package com.rewa.spring.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.hibernate.data.Employee;

@Component
@Transactional
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
	public void register(Employee emp) {
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		// Save employee, saving behavior get done in a transactional manner
		session.save(emp);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			List<Employee> result = session.createCriteria(Employee.class).list();
			System.out.println("getEmployees: " + ((result != null) ? result.size() : 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get employee by id
	 * @return
	 */
	public List<Employee> getEmployeeById(long id) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("empId", id));
			@SuppressWarnings("unchecked")
			List<Employee> result = criteria.list();
			System.out.println("getEmployeeById (" + id + ")" + ((result != null) ? result.size() : 0));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
