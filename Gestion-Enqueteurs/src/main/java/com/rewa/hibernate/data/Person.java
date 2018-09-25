package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rewa_person database table.
 * 
 */
@Entity
@Table(name="rewa_person")
@NamedQuery(name="Person.findAll", query="SELECT r FROM Person r")
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

	@Lob
	private byte[] picture;

	//bi-directional many-to-one association to RewaComment
	@OneToMany(mappedBy="rewaPerson")
	private List<RewaComment> rewaComments;

	//bi-directional many-to-one association to RewaCustomer
	@OneToMany(mappedBy="rewaPerson1")
	private List<RewaCustomer> rewaCustomers1;

	//bi-directional many-to-one association to RewaCustomer
	@OneToMany(mappedBy="rewaPerson2")
	private List<RewaCustomer> rewaCustomers2;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="createdBy")
	private Person rewaPerson1;

	//bi-directional many-to-one association to RewaPerson
	@OneToMany(mappedBy="rewaPerson1")
	private List<Person> rewaPersons1;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="modifiedBy")
	private Person rewaPerson2;

	//bi-directional many-to-one association to RewaPerson
	@OneToMany(mappedBy="rewaPerson2")
	private List<Person> rewaPersons2;

	//bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name="status")
	private RewaStatus rewaStatus;

	//bi-directional many-to-one association to RewaPersonLevel
	@OneToMany(mappedBy="rewaPerson1")
	private List<RewaPersonLevel> rewaPersonLevels1;

	//bi-directional many-to-one association to RewaPersonLevel
	@OneToMany(mappedBy="rewaPerson2")
	private List<RewaPersonLevel> rewaPersonLevels2;

	//bi-directional many-to-one association to RewaPersonRole
	@OneToMany(mappedBy="rewaPerson")
	private List<RewaPersonRole> rewaPersonRoles;

	//bi-directional many-to-one association to RewaTeam
	@OneToMany(mappedBy="rewaPerson")
	private List<RewaTeam> rewaTeams;

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

	public List<RewaComment> getRewaComments() {
		return this.rewaComments;
	}

	public void setRewaComments(List<RewaComment> rewaComments) {
		this.rewaComments = rewaComments;
	}

	public RewaComment addRewaComment(RewaComment rewaComment) {
		getRewaComments().add(rewaComment);
		rewaComment.setRewaPerson(this);

		return rewaComment;
	}

	public RewaComment removeRewaComment(RewaComment rewaComment) {
		getRewaComments().remove(rewaComment);
		rewaComment.setRewaPerson(null);

		return rewaComment;
	}

	public List<RewaCustomer> getRewaCustomers1() {
		return this.rewaCustomers1;
	}

	public void setRewaCustomers1(List<RewaCustomer> rewaCustomers1) {
		this.rewaCustomers1 = rewaCustomers1;
	}

	public RewaCustomer addRewaCustomers1(RewaCustomer rewaCustomers1) {
		getRewaCustomers1().add(rewaCustomers1);
		rewaCustomers1.setRewaPerson1(this);

		return rewaCustomers1;
	}

	public RewaCustomer removeRewaCustomers1(RewaCustomer rewaCustomers1) {
		getRewaCustomers1().remove(rewaCustomers1);
		rewaCustomers1.setRewaPerson1(null);

		return rewaCustomers1;
	}

	public List<RewaCustomer> getRewaCustomers2() {
		return this.rewaCustomers2;
	}

	public void setRewaCustomers2(List<RewaCustomer> rewaCustomers2) {
		this.rewaCustomers2 = rewaCustomers2;
	}

	public RewaCustomer addRewaCustomers2(RewaCustomer rewaCustomers2) {
		getRewaCustomers2().add(rewaCustomers2);
		rewaCustomers2.setRewaPerson2(this);

		return rewaCustomers2;
	}

	public RewaCustomer removeRewaCustomers2(RewaCustomer rewaCustomers2) {
		getRewaCustomers2().remove(rewaCustomers2);
		rewaCustomers2.setRewaPerson2(null);

		return rewaCustomers2;
	}

	public Person getRewaPerson1() {
		return this.rewaPerson1;
	}

	public void setRewaPerson1(Person rewaPerson1) {
		this.rewaPerson1 = rewaPerson1;
	}

	public List<Person> getRewaPersons1() {
		return this.rewaPersons1;
	}

	public void setRewaPersons1(List<Person> rewaPersons1) {
		this.rewaPersons1 = rewaPersons1;
	}

	public Person addRewaPersons1(Person rewaPersons1) {
		getRewaPersons1().add(rewaPersons1);
		rewaPersons1.setRewaPerson1(this);

		return rewaPersons1;
	}

	public Person removeRewaPersons1(Person rewaPersons1) {
		getRewaPersons1().remove(rewaPersons1);
		rewaPersons1.setRewaPerson1(null);

		return rewaPersons1;
	}

	public Person getRewaPerson2() {
		return this.rewaPerson2;
	}

	public void setRewaPerson2(Person rewaPerson2) {
		this.rewaPerson2 = rewaPerson2;
	}

	public List<Person> getRewaPersons2() {
		return this.rewaPersons2;
	}

	public void setRewaPersons2(List<Person> rewaPersons2) {
		this.rewaPersons2 = rewaPersons2;
	}

	public Person addRewaPersons2(Person rewaPersons2) {
		getRewaPersons2().add(rewaPersons2);
		rewaPersons2.setRewaPerson2(this);

		return rewaPersons2;
	}

	public Person removeRewaPersons2(Person rewaPersons2) {
		getRewaPersons2().remove(rewaPersons2);
		rewaPersons2.setRewaPerson2(null);

		return rewaPersons2;
	}

	public RewaStatus getRewaStatus() {
		return this.rewaStatus;
	}

	public void setRewaStatus(RewaStatus rewaStatus) {
		this.rewaStatus = rewaStatus;
	}

	public List<RewaPersonLevel> getRewaPersonLevels1() {
		return this.rewaPersonLevels1;
	}

	public void setRewaPersonLevels1(List<RewaPersonLevel> rewaPersonLevels1) {
		this.rewaPersonLevels1 = rewaPersonLevels1;
	}

	public RewaPersonLevel addRewaPersonLevels1(RewaPersonLevel rewaPersonLevels1) {
		getRewaPersonLevels1().add(rewaPersonLevels1);
		rewaPersonLevels1.setRewaPerson1(this);

		return rewaPersonLevels1;
	}

	public RewaPersonLevel removeRewaPersonLevels1(RewaPersonLevel rewaPersonLevels1) {
		getRewaPersonLevels1().remove(rewaPersonLevels1);
		rewaPersonLevels1.setRewaPerson1(null);

		return rewaPersonLevels1;
	}

	public List<RewaPersonLevel> getRewaPersonLevels2() {
		return this.rewaPersonLevels2;
	}

	public void setRewaPersonLevels2(List<RewaPersonLevel> rewaPersonLevels2) {
		this.rewaPersonLevels2 = rewaPersonLevels2;
	}

	public RewaPersonLevel addRewaPersonLevels2(RewaPersonLevel rewaPersonLevels2) {
		getRewaPersonLevels2().add(rewaPersonLevels2);
		rewaPersonLevels2.setRewaPerson2(this);

		return rewaPersonLevels2;
	}

	public RewaPersonLevel removeRewaPersonLevels2(RewaPersonLevel rewaPersonLevels2) {
		getRewaPersonLevels2().remove(rewaPersonLevels2);
		rewaPersonLevels2.setRewaPerson2(null);

		return rewaPersonLevels2;
	}

	public List<RewaPersonRole> getRewaPersonRoles() {
		return this.rewaPersonRoles;
	}

	public void setRewaPersonRoles(List<RewaPersonRole> rewaPersonRoles) {
		this.rewaPersonRoles = rewaPersonRoles;
	}

	public RewaPersonRole addRewaPersonRole(RewaPersonRole rewaPersonRole) {
		getRewaPersonRoles().add(rewaPersonRole);
		rewaPersonRole.setRewaPerson(this);

		return rewaPersonRole;
	}

	public RewaPersonRole removeRewaPersonRole(RewaPersonRole rewaPersonRole) {
		getRewaPersonRoles().remove(rewaPersonRole);
		rewaPersonRole.setRewaPerson(null);

		return rewaPersonRole;
	}

	public List<RewaTeam> getRewaTeams() {
		return this.rewaTeams;
	}

	public void setRewaTeams(List<RewaTeam> rewaTeams) {
		this.rewaTeams = rewaTeams;
	}

	public RewaTeam addRewaTeam(RewaTeam rewaTeam) {
		getRewaTeams().add(rewaTeam);
		rewaTeam.setRewaPerson(this);

		return rewaTeam;
	}

	public RewaTeam removeRewaTeam(RewaTeam rewaTeam) {
		getRewaTeams().remove(rewaTeam);
		rewaTeam.setRewaPerson(null);

		return rewaTeam;
	}

}