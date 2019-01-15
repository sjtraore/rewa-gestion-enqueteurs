package com.rewa.hibernate.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


/**
 * The persistent class for the rewa_customer database table.
 * 
 */
@Entity
@Table(name="rewa_customer")
@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="SELECT r FROM Customer r"),
	@NamedQuery(name="Customer.findByStatus", query="SELECT r FROM Customer r where r.status = :status")
})
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
	private Status status;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="createdBy")
	private Person createdBy;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="modifiedBy")
	private Person modifiedBy;

	//bi-directional many-to-one association to RewaStudy
	@OneToMany(mappedBy="customer")
	private List<Study> studies;

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

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Person getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	public Person getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Person modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<Study> getStudies() {
		return this.studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public Study addStudy(Study study) {
		getStudies().add(study);
		study.setCustomer(this);

		return study;
	}

	public Study removeStudy(Study study) {
		getStudies().remove(study);
		study.setCustomer(null);

		return study;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCustomer;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (idCustomer != other.idCustomer)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [idCustomer=" + idCustomer + ", name=" + name + ", status=" + status + "]";
	}

}