package com.rewa;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Status;

public class Main {
	public static void main(String [] args){
		Session session = getSession();
		// Begin transaction, if you would like save your instances, your calling of save must be associated with a transaction
		session.getTransaction().begin();
		
		Person person = getPerson();
		session.save(person);
		
		// Create employee
		/*Employee emp = new Employee();
		emp.setEmpName("Peter Jousha");
		emp.setEmpSalary(2000);
		emp.setEmpHireDate(new Date());
		// Save
		session.save(emp);
		*/
		// Commit, calling of commit will cause save an instance of employee
		session.getTransaction().commit();
	}

	private static Person getPerson() {
		Person person = new Person();
		person.setFirstname("Jacques");
		person.setLastname("Traoré");
		person.setCreatedDate(new Date());
		person.setModifiedDate(new Date());
		Status status = new Status();
		status.setIdStatus(1);
		person.setRewaStatus(status);
		return person;
	}

	private static Session getSession() {
		// Create a configuration instance
		Configuration configuration = new Configuration();
		// Provide configuration file
		configuration.configure("hibernate.cfg.xml");
		// Build a SessionFactory
		SessionFactory factory = configuration.buildSessionFactory();
		// Get current session, current session is already associated with Thread
		Session session = factory.getCurrentSession();
		return session;
	}
	
}
