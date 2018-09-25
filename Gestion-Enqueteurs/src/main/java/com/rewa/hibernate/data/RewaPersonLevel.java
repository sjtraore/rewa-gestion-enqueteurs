package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_person_level database table.
 * 
 */
@Entity
@Table(name="rewa_person_level")
@NamedQuery(name="RewaPersonLevel.findAll", query="SELECT r FROM RewaPersonLevel r")
public class RewaPersonLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RewaPersonLevelPK id;

	private int baseNotation;

	private int notation;

	private String observation;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="evaluatedBy")
	private Person rewaPerson1;

	//bi-directional many-to-one association to RewaField
	@ManyToOne
	@JoinColumn(name="idField")
	private RewaField rewaField;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="idPerson")
	private Person rewaPerson2;

	public RewaPersonLevel() {
	}

	public RewaPersonLevelPK getId() {
		return this.id;
	}

	public void setId(RewaPersonLevelPK id) {
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
		return this.rewaPerson1;
	}

	public void setRewaPerson1(Person rewaPerson1) {
		this.rewaPerson1 = rewaPerson1;
	}

	public RewaField getRewaField() {
		return this.rewaField;
	}

	public void setRewaField(RewaField rewaField) {
		this.rewaField = rewaField;
	}

	public Person getRewaPerson2() {
		return this.rewaPerson2;
	}

	public void setRewaPerson2(Person rewaPerson2) {
		this.rewaPerson2 = rewaPerson2;
	}

}