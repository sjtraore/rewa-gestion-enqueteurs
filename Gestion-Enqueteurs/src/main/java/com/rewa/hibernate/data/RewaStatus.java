package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rewa_status database table.
 * 
 */
@Entity
@Table(name="rewa_status")
@NamedQuery(name="RewaStatus.findAll", query="SELECT r FROM RewaStatus r")
public class RewaStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStatus;

	private String status;

	//bi-directional many-to-one association to RewaCustomer
	@OneToMany(mappedBy="rewaStatus")
	private List<RewaCustomer> rewaCustomers;

	//bi-directional many-to-one association to RewaPerson
	@OneToMany(mappedBy="rewaStatus")
	private List<Person> rewaPersons;

	//bi-directional many-to-one association to RewaPersonRole
	@OneToMany(mappedBy="rewaStatus")
	private List<RewaPersonRole> rewaPersonRoles;

	//bi-directional many-to-one association to RewaStudy
	@OneToMany(mappedBy="rewaStatus")
	private List<RewaStudy> rewaStudies;

	public RewaStatus() {
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RewaCustomer> getRewaCustomers() {
		return this.rewaCustomers;
	}

	public void setRewaCustomers(List<RewaCustomer> rewaCustomers) {
		this.rewaCustomers = rewaCustomers;
	}

	public RewaCustomer addRewaCustomer(RewaCustomer rewaCustomer) {
		getRewaCustomers().add(rewaCustomer);
		rewaCustomer.setRewaStatus(this);

		return rewaCustomer;
	}

	public RewaCustomer removeRewaCustomer(RewaCustomer rewaCustomer) {
		getRewaCustomers().remove(rewaCustomer);
		rewaCustomer.setRewaStatus(null);

		return rewaCustomer;
	}

	public List<Person> getRewaPersons() {
		return this.rewaPersons;
	}

	public void setRewaPersons(List<Person> rewaPersons) {
		this.rewaPersons = rewaPersons;
	}

	public Person addRewaPerson(Person rewaPerson) {
		getRewaPersons().add(rewaPerson);
		rewaPerson.setRewaStatus(this);

		return rewaPerson;
	}

	public Person removeRewaPerson(Person rewaPerson) {
		getRewaPersons().remove(rewaPerson);
		rewaPerson.setRewaStatus(null);

		return rewaPerson;
	}

	public List<RewaPersonRole> getRewaPersonRoles() {
		return this.rewaPersonRoles;
	}

	public void setRewaPersonRoles(List<RewaPersonRole> rewaPersonRoles) {
		this.rewaPersonRoles = rewaPersonRoles;
	}

	public RewaPersonRole addRewaPersonRole(RewaPersonRole rewaPersonRole) {
		getRewaPersonRoles().add(rewaPersonRole);
		rewaPersonRole.setRewaStatus(this);

		return rewaPersonRole;
	}

	public RewaPersonRole removeRewaPersonRole(RewaPersonRole rewaPersonRole) {
		getRewaPersonRoles().remove(rewaPersonRole);
		rewaPersonRole.setRewaStatus(null);

		return rewaPersonRole;
	}

	public List<RewaStudy> getRewaStudies() {
		return this.rewaStudies;
	}

	public void setRewaStudies(List<RewaStudy> rewaStudies) {
		this.rewaStudies = rewaStudies;
	}

	public RewaStudy addRewaStudy(RewaStudy rewaStudy) {
		getRewaStudies().add(rewaStudy);
		rewaStudy.setRewaStatus(this);

		return rewaStudy;
	}

	public RewaStudy removeRewaStudy(RewaStudy rewaStudy) {
		getRewaStudies().remove(rewaStudy);
		rewaStudy.setRewaStatus(null);

		return rewaStudy;
	}

}