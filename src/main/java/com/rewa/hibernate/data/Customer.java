package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rewa_customer database table.
 * 
 */
@Entity
@Table(name="rewa_customer")
@NamedQuery(name="Customer.findAll", query="SELECT r FROM Customer r")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCustomer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String name;

	//bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name="idStatus")
	private Status rewaStatus;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="createdBy")
	private Person rewaPerson1;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="modifiedBy")
	private Person rewaPerson2;

	//bi-directional many-to-one association to RewaStudy
	@OneToMany(mappedBy="rewaCustomer")
	private List<Study> rewaStudies;

	public Customer() {
	}

	public int getIdCustomer() {
		return this.idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getRewaStatus() {
		return this.rewaStatus;
	}

	public void setRewaStatus(Status rewaStatus) {
		this.rewaStatus = rewaStatus;
	}

	public Person getRewaPerson1() {
		return this.rewaPerson1;
	}

	public void setRewaPerson1(Person rewaPerson1) {
		this.rewaPerson1 = rewaPerson1;
	}

	public Person getRewaPerson2() {
		return this.rewaPerson2;
	}

	public void setRewaPerson2(Person rewaPerson2) {
		this.rewaPerson2 = rewaPerson2;
	}

	public List<Study> getRewaStudies() {
		return this.rewaStudies;
	}

	public void setRewaStudies(List<Study> rewaStudies) {
		this.rewaStudies = rewaStudies;
	}

	public Study addRewaStudy(Study rewaStudy) {
		getRewaStudies().add(rewaStudy);
		rewaStudy.setRewaCustomer(this);

		return rewaStudy;
	}

	public Study removeRewaStudy(Study rewaStudy) {
		getRewaStudies().remove(rewaStudy);
		rewaStudy.setRewaCustomer(null);

		return rewaStudy;
	}

}