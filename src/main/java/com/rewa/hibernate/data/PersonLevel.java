package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_person_level database table.
 * 
 */
@Entity
@Table(name="rewa_person_level")
@NamedQuery(name="PersonLevel.findAll", query="SELECT r FROM PersonLevel r")
public class PersonLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonLevelPK id;

	private int baseNotation;

	private int notation;

	private String observation;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="evaluatedBy", insertable = false, updatable = false)
	private Person evaluator;

	//bi-directional many-to-one association to RewaField
	@ManyToOne
	@JoinColumn(name="idField", insertable = false, updatable = false)
	private Field field;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="idPerson", insertable = false, updatable = false)
	private Person person;

	public PersonLevel() {
	}

	public PersonLevelPK getId() {
		return this.id;
	}

	public void setId(PersonLevelPK id) {
		this.id = id;
	}

	public int getBaseNotation() {
		return this.baseNotation;
	}

	public void setBaseNotation(int baseNotation) {
		this.baseNotation = baseNotation;
	}

	public int getNotation() {
		return this.notation;
	}

	public void setNotation(int notation) {
		this.notation = notation;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Person getEvaluator() {
		return this.evaluator;
	}

	public void setEvaluator(Person evaluator) {
		this.evaluator = evaluator;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + notation;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		PersonLevel other = (PersonLevel) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (notation != other.notation)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}



}