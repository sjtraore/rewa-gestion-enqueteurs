package com.rewa.hibernate.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name = "EMPLOYEE")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="EMP_ID")
	private long empId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EMP_HIRE_DATE")
	private Date empHireDate;

	@Column(name="EMP_NAME")
	private String empName;

	@Column(name="EMP_SALARY")
	private double empSalary;

	public Employee() {
	}

	public long getEmpId() {
		return this.empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public Date getEmpHireDate() {
		return this.empHireDate;
	}

	public void setEmpHireDate(Date empHireDate) {
		this.empHireDate = empHireDate;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getEmpSalary() {
		return this.empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

}