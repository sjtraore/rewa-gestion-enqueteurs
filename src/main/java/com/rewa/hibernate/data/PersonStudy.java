package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_person_role database table.
 * 
 */
@Entity
@Table(name="rewa_person_study")
@NamedQuery(name="PersonStudy.findAll", query="SELECT r FROM PersonStudy r")
public class PersonStudy implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonStudyPK id;

	@Column(name="idPerson", insertable = false, updatable = false)
	private Person person;

	@Column(name="idStudy", insertable = false, updatable = false)
	private Study study;

	public PersonStudy() {
	}

	public PersonStudy(Person person, Study study) {
		super();
		this.person = person;
		this.study = study;
	}

	public PersonStudyPK getId() {
		return id;
	}

	public void setId(PersonStudyPK id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}



}