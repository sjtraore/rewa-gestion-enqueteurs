package com.rewa.hibernate.data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * The persistent class for the rewa_study database table.
 * 
 */
@Entity
@Table(name = "rewa_study")
@NamedQueries({ 
	@NamedQuery(name = "Study.findAll", query = "SELECT r FROM Study r"),
	@NamedQuery(name = "Study.findByIdEagerMode", query = "SELECT r FROM Study r "
			+ "left join fetch r.enqueteurs "
			+ "where r.idStudy = :idStudy"),
	@NamedQuery(name = "Study.findByStatus", query = "SELECT r FROM Study r where r.status = :status") 
})
public class Study implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStudy;

	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	// bi-directional many-to-one association to RewaCustomer
	@ManyToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idCreatedBy")
	private Person createdBy;

	@ManyToOne
	@JoinColumn(name = "idModifiedBy")
	private Person modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	// bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "idSupervisor")
	private Person supervisor;

	@ManyToOne
	@JoinColumn(name = "idValidator")
	private Person validator;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date validateDate;

	@ManyToOne
	@JoinColumn(name = "idCloser")
	private Person closer;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date closeDate;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "rewa_person_study", 
			joinColumns = {@JoinColumn(name = "idStudy") }, 
			inverseJoinColumns = {@JoinColumn(name = "idPerson") })
	private Set<Person> enqueteurs;

	public Study() {
	}

	public int getIdStudy() {
		return this.idStudy;
	}

	public void setIdStudy(int idStudy) {
		this.idStudy = idStudy;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStudy;
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
		Study other = (Study) obj;
		if (idStudy != other.idStudy)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Study [idStudy=" + idStudy + ", title=" + title + ", endDate=" + endDate + ", startDate=" + startDate
				+ "]";
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	public Person getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Person modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Person getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Person supervisor) {
		this.supervisor = supervisor;
	}

	public Set<Person> getEnqueteurs() {
		return enqueteurs;
	}

	public void setEnqueteurs(Set<Person> enqueteurs) {
		this.enqueteurs = enqueteurs;
	}
	
	public Person addEnqueteur(Person person) {
		if (getEnqueteurs() == null) {
			setEnqueteurs(new HashSet<Person>());
		}
		getEnqueteurs().add(person);

		return person;
	}

	public Person removeEnqueteur(Person person) {
		getEnqueteurs().remove(person);

		return person;
	}

	public Person getValidator() {
		return validator;
	}

	public void setValidator(Person validator) {
		this.validator = validator;
	}

	public Date getValidateDate() {
		return validateDate;
	}

	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}

	public Person getCloser() {
		return closer;
	}

	public void setCloser(Person closer) {
		this.closer = closer;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	} 

}