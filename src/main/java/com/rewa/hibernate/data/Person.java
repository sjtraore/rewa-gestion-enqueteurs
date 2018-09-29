package com.rewa.hibernate.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the rewa_person database table.
 * 
 */
@Entity
@Table(name = "rewa_person")
@NamedQuery(name = "Person.findAll", query = "SELECT r FROM Person r")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPerson;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String firstname;

	private String lastname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String password;
	
	private String username;

	@Lob
	private byte[] picture;

	// bi-directional many-to-one association to PersonRole
	@OneToMany(mappedBy = "person")
	private List<PersonRole> personRoles;

	// bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name = "createdBy")
	private Person creator;

	// bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name = "modifiedBy")
	private Person updator;

	// bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name = "status")
	private Status status;

	public Person() {
	}

	public int getIdPerson() {
		return this.idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Person getCreator() {
		return this.creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Person getUpdator() {
		return this.updator;
	}

	public void setUpdator(Person updator) {
		this.updator = updator;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status rewaStatus) {
		this.status = rewaStatus;
	}

	public List<PersonRole> getPersonRoles() {
		return personRoles;
	}

	public void setPersonRoles(List<PersonRole> personRoles) {
		this.personRoles = personRoles;
	}

	public PersonRole addPersonRole(PersonRole PersonRole) {
		getPersonRoles().add(PersonRole);
		PersonRole.setPerson(this);

		return PersonRole;
	}

	public PersonRole removePersonRole(PersonRole PersonRole) {
		getPersonRoles().remove(PersonRole);
		PersonRole.setPerson(null);

		return PersonRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}