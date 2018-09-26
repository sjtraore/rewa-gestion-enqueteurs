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
	@JoinColumn(name="evaluatedBy")
	private Person evaluator;

	//bi-directional many-to-one association to RewaField
	@ManyToOne
	@JoinColumn(name="idField", insertable = false, updatable = false)
	private Field rewaField;

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

	public Person getRewaPerson1() {
		return this.evaluator;
	}

	public void setRewaPerson1(Person rewaPerson1) {
		this.evaluator = rewaPerson1;
	}

	public Field getRewaField() {
		return this.rewaField;
	}

	public void setRewaField(Field rewaField) {
		this.rewaField = rewaField;
	}

	public Person getRewaPerson2() {
		return this.person;
	}

	public void setRewaPerson2(Person rewaPerson2) {
		this.person = rewaPerson2;
	}

}