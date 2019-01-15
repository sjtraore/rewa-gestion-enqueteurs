package com.rewa.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//import org.apache.log4j.Logger;

import com.rewa.beans.CustomerBean;
import com.rewa.beans.PersonBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Customer;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Status;
import com.rewa.spring.service.CommonService;
import com.rewa.spring.service.CustomerService;
import com.rewa.spring.service.PersonService;
import com.rewa.utils.PersonUtils;
import com.rewa.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class CustomerManage {
	// private static final Logger log = Logger.getLogger(CustomerManage.class);
	private CustomerBean customerBean;
	private List<CustomerBean> customerBeanList;
	private Customer customer;

	@ManagedProperty("#{personService}")
	private PersonService personService;

	@ManagedProperty("#{customerService}")
	private CustomerService customerService;

	@ManagedProperty("#{commonService}")
	private CommonService commonService;

	private Status status;

	@PostConstruct
	public void init() {
		status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);
		customer = getCustomerByCustomerBean(customerBean);
		List<Customer> customers = customerService.getCustomersByStatus(status);
		customerBeanList = customerBeanListFromCustomer(customers);
	}

	public List<CustomerBean> customerBeanListFromCustomer(List<Customer> customers) {
		List<CustomerBean> result = null;
		if (customers != null) {
			result = new ArrayList<CustomerBean>();
			for (Customer customer : customers) {
				result.add(getCustomerBeanByCustomer(customer));
			}
		}
		return result;
	}

	public String register() {
		Date createdDate = customerBean.getCreatedDate();
		int connectedUserId = SessionUtils.getConnectedPerson().getIdPerson();
		if (createdDate == null) {
			customerBean.setCreatedDate(new Date());
			customerBean.setCreatorId(connectedUserId);
		}

		customerBean.setModifierId(connectedUserId);
		customerBean.setModifiedDate(new Date());
		customer = getCustomerByCustomerBean(customerBean);
		customerService.save(customer);

		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Le client " + this.customerBean.getName() + " a été enregistré avec succés"));
		return Constant.VIEW_CUSTOMERS_PAGE_OUTCOME;
	}

	public String forwardToAddCustomer(CustomerBean customerBean) {
		customerBean = (customerBean == null) ? new CustomerBean() : customerBean;
		this.customerBean = customerBean;
		return Constant.VIEW_ADD_CUSTOMER_PAGE_OUTCOME;
	}

	public String cancelRegistration() {
		return Constant.VIEW_CUSTOMERS_PAGE_OUTCOME;
	}

	public void disableCustomer(CustomerBean customerBean) {
		Customer customer = customerService.getCustomerById(customerBean.getId());
		Status inactiveStatus = commonService.getStatusByStatusId(Constant.INACTIVE_STATUS_ID);
		customer.setStatus(inactiveStatus);
		customerService.save(customer);
	}

	/**
	 * For Admin only
	 * 
	 * @param customerBean
	 */
	public void deleteCustomer(CustomerBean customerBean) {
		// TODO ensure that connected user is admin
		Customer customer = customerService.getCustomerById(customerBean.getId());
		customerService.delete(customer);
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	// get customer by id

	// save customer
	public String saveCustomer() {
		customer = getCustomerByCustomerBean(customerBean);
		return null;

	}

	private Customer getCustomerByCustomerBean(CustomerBean customerBean) {
		if (customerBean != null) {
			Customer customer = new Customer();
			customer.setIdCustomer(customerBean.getId());
			customer.setName(customerBean.getName());
			Date createdDate = customerBean.getCreatedDate();
			customer.setCreatedDate(createdDate);
			customer.setModifiedDate(customerBean.getModifiedDate());

			Person creator = personService.getPersonById(customerBean.getCreatorId());
			customer.setCreatedBy(creator);

			Person modifier = personService.getPersonById(customerBean.getModifierId());
			customer.setModifiedBy(modifier);

			status = commonService.getStatusByStatusName(customerBean.getStatus());
			customer.setStatus(status);

			return customer;
		}
		return null;
	}

	private CustomerBean getCustomerBeanByCustomer(Customer customer) {
		if (customer != null) {
			CustomerBean customerBean = new CustomerBean();
			customerBean.setId(customer.getIdCustomer());
			customerBean.setName(customer.getName());
			customerBean.setCreatedDate(customer.getCreatedDate());
			customerBean.setModifiedDate(customer.getModifiedDate());

			Person creator = customer.getCreatedBy();
			PersonBean creatorBean = PersonUtils.getPersonBeanByPerson(creator, true);
			if(creatorBean != null)
				customerBean.setCreatedBy(creatorBean.getFullname());

			Person modifier = customer.getModifiedBy();
			PersonBean modifierBean = PersonUtils.getPersonBeanByPerson(modifier, true);
			if(modifierBean != null)
				customerBean.setModifiedBy(modifierBean.getFullname());
			return customerBean;
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

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public List<CustomerBean> getCustomerBeanList() {
		return customerBeanList;
	}

	public void setCustomerBeanList(List<CustomerBean> customerBeanList) {
		this.customerBeanList = customerBeanList;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
