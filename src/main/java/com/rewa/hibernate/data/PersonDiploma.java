package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_person_role database table.
 * 
 */
@Entity
@Table(name="rewa_person_diploma")
@NamedQuery(name="PersonDiploma.findAll", query="SELECT r FROM PersonDiploma r")
public class PersonDiploma implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonDiplomaPK id;

	@Column(name="idPerson", nullable = false, insertable = false, updatable = false)
	private Person person;

	@Column(name="idDiploma", nullable = false, insertable = false, updatable = false)
	private Diploma diploma;

	public PersonDiploma() {
	}

	public PersonDiploma(Person person, Diploma diploma) {
		super();
		this.person = person;
		this.diploma = diploma;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person rewaPerson) {
		this.person = rewaPerson;
	}

	public PersonDiplomaPK getId() {
		return id;
	}

	public void setId(PersonDiplomaPK id) {
		this.id = id;
	}

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

}