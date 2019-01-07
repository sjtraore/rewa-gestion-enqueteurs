package com.rewa.managedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.rewa.beans.CustomerBean;
import com.rewa.hibernate.data.Customer;
import com.rewa.hibernate.data.Person;
import com.rewa.spring.service.PersonService;

@ManagedBean
@SessionScoped
public class CustomerManage {
	private static final Logger log = Logger.getLogger(CustomerManage.class);
	private CustomerBean customerBean;
	private Customer customer;
	
	@ManagedProperty("#{personService}")
	private PersonService personService;

	@PostConstruct
	public void init() {
		customer = getCustomerByCustomerBean(customerBean);	
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	// List of customers

	// get customer by id

	// save customer
	public String saveCustomer() {
		customer = getCustomerByCustomerBean(customerBean);
		return null;
		
	}

	private Customer getCustomerByCustomerBean(CustomerBean customerBean) {
		if(customerBean != null) {
			Customer customer = new Customer();
			customer.setIdCustomer(customerBean.getId());
			customer.setName(customerBean.getName());
			customer.setCreatedDate(customerBean.getCreatedDate());
			customer.setModifiedDate(customerBean.getModifiedDate());
			
			Person creator = personService.getPersonById(customerBean.getCreatorId());
			customer.setCreatedBy(creator);
			
			Person modifier = personService.getPersonById(customerBean.getModifierId());
			customer.setModifiedBy(modifier);
		}
		return null;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	// soft delete customer

	// hard delete customer

}
