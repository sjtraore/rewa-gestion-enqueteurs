package com.rewa.spring.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.hibernate.data.Customer;
import com.rewa.hibernate.data.Status;

@Component
@Transactional
public class CustomerService {
	
	private static final Logger log = Logger.getLogger(CustomerService.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Customer customer) {
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		customer.setModifiedDate(new Date());
		// Save person, saving behavior get done in a transactional manner
		if (customer.getIdCustomer() == 0) {
			customer.setCreatedDate(new Date());
			session.save(customer);
		} else {
			session.merge(customer);
		}
	}
	
	public Customer getCustomerById(int idCustomer) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.idEq(idCustomer));
			return (Customer) criteria.uniqueResult();
		} catch (Exception e) {
			log.debug(e, e);
			return null;
		}
	}
	
	public Customer getCustomerByName(String name) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.eq("name", name));
			return (Customer) criteria.uniqueResult();
		} catch (Exception e) {
			log.debug(e, e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomersByStatus(Status status) {
		List<Customer> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			//Don't know why this is returning duplicates
			//Criteria criteria = session.createCriteria(Customer.class)
			//		.add(Restrictions.eq("status", status));
			
			Query query = session.getNamedQuery("Customer.findByStatus").setParameter("status", status);
			result = (List<Customer>) query.list();
		} catch (Exception e) {
			log.debug(e, e);
		}
		return result;
	}
	
	public Set<Customer> getSetOfCustomersByList(List<Customer> customers){
		return new HashSet<Customer>(customers);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void delete(Customer customer) {
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		session.delete(customer);
	}
	
}
