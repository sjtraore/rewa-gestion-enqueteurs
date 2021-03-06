package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_person_role database table.
 * 
 */
@Entity
@Table(name="rewa_person_role")
@NamedQuery(name="PersonRole.findAll", query="SELECT r FROM PersonRole r")
public class PersonRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonRolePK id;

	@Column(name="idPerson", insertable = false, updatable = false)
	private Person person;

	@Column(name="idRole", insertable = false, updatable = false)
	private Role role;

	public PersonRole() {
	}

	public PersonRole(Person person, Role role) {
		super();
		this.person = person;
		this.role = role;
	}

	public PersonRolePK getId() {
		return this.id;
	}

	public void setId(PersonRolePK id) {
		this.id = id;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person rewaPerson) {
		this.person = rewaPerson;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role rewaRole) {
		this.role = rewaRole;
	}

}