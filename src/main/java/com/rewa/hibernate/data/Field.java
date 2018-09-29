package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rewa_field database table.
 * 
 */
@Entity
@Table(name="rewa_field")
@NamedQuery(name="Field.findAll", query="SELECT r FROM Field r")
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idField;

	private String field;

	//bi-directional many-to-one association to RewaPersonLevel
	@OneToMany(mappedBy="rewaField")
	private List<PersonLevel> rewaPersonLevels;

	public Field() {
	}

	public int getIdField() {
		return this.idField;
	}

	public void setIdField(int idField) {
		this.idField = idField;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<PersonLevel> getRewaPersonLevels() {
		return this.rewaPersonLevels;
	}

	public void setRewaPersonLevels(List<PersonLevel> rewaPersonLevels) {
		this.rewaPersonLevels = rewaPersonLevels;
	}

	public PersonLevel addRewaPersonLevel(PersonLevel rewaPersonLevel) {
		getRewaPersonLevels().add(rewaPersonLevel);
		rewaPersonLevel.setRewaField(this);

		return rewaPersonLevel;
	}

	public PersonLevel removeRewaPersonLevel(PersonLevel rewaPersonLevel) {
		getRewaPersonLevels().remove(rewaPersonLevel);
		rewaPersonLevel.setRewaField(null);

		return rewaPersonLevel;
	}

}