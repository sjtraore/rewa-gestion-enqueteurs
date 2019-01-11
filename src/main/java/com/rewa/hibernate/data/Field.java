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
	@OneToMany(mappedBy="field")
	private List<PersonLevel> personLevels;

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

	public List<PersonLevel> getPersonLevels() {
		return this.personLevels;
	}

	public void setPersonLevels(List<PersonLevel> personLevels) {
		this.personLevels = personLevels;
	}

	public PersonLevel addPersonLevel(PersonLevel personLevel) {
		getPersonLevels().add(personLevel);
		personLevel.setField(this);

		return personLevel;
	}

	public PersonLevel removePersonLevel(PersonLevel personLevel) {
		getPersonLevels().remove(personLevel);
		personLevel.setField(null);

		return personLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + idField;
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
		Field other = (Field) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (idField != other.idField)
			return false;
		return true;
	}

}