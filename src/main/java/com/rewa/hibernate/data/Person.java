package com.rewa.hibernate.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "rewa_person_role", joinColumns = {
			@JoinColumn(name = "idPerson", referencedColumnName = "idPerson") }, inverseJoinColumns = {
					@JoinColumn(name = "idRole", referencedColumnName = "idRole") })
	private List<Role> roles;

	@OneToMany(mappedBy = "owner")
	private Set<Coordinate> coordinates;

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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "rewa_person_diploma", joinColumns = {
			@JoinColumn(name = "idPerson", referencedColumnName = "idPerson") }, inverseJoinColumns = {
					@JoinColumn(name = "idDiploma", referencedColumnName = "idDiploma") })
	private Set<Diploma> diplomas;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		if (getRoles() == null) {
			setRoles(new ArrayList<Role>());
		}
		getRoles().add(role);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);

		return role;
	}

	public Diploma addDiploma(Diploma diploma) {
		if (getDiplomas() == null) {
			setDiplomas(new HashSet<Diploma>());
		}
		getDiplomas().add(diploma);

		return diploma;
	}

	public Diploma removeRole(Diploma diploma) {
		getDiplomas().remove(diploma);

		return diploma;
	}

	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPerson;
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
		Person other = (Person) obj;
		if (idPerson != other.idPerson)
			return false;
		return true;
	}

	public Set<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Set<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinate addCoordinate(Coordinate coordinate) {
		if (getCoordinates() == null) {
			setCoordinates(new HashSet<Coordinate>());
		}
		getCoordinates().add(coordinate);
		return coordinate;
	}

	public Coordinate removeCoordinate(Coordinate coordinate) {
		getCoordinates().remove(coordinate);
		return coordinate;
	}

	public Set<Diploma> getDiplomas() {
		return diplomas;
	}

	public void setDiplomas(Set<Diploma> diplomas) {
		this.diplomas = diplomas;
	}

}