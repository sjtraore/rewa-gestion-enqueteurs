package com.rewa.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.rewa.hibernate.data.Employee;
import com.rewa.spring.service.EmployeeService;

@ManagedBean
@SessionScoped
public class EmployeeManage {

	private List<Employee> employees;
	private List<Employee> filteredEmployees;
	private Employee employee = new Employee();
	
	@ManagedProperty("#{employeeService}")
	private EmployeeService employeeService;

	@PostConstruct
	public void init() {
		employees = employeeService.getEmployees();
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeById(long id) {
		return employeeService.getEmployeeById(id);
	}

	public String register() {
		// Calling Business Service
		employeeService.register(employee);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("The Employee " + this.employee.getEmpName() + " Is Registered Successfully"));
		return "";
	}

	public List<Employee> getFilteredEmployees() {
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}
}
