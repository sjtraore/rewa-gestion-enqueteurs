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
@NamedQuery(name="RewaField.findAll", query="SELECT r FROM RewaField r")
public class RewaField implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idField;

	private String field;

	//bi-directional many-to-one association to RewaPersonLevel
	@OneToMany(mappedBy="rewaField")
	private List<RewaPersonLevel> rewaPersonLevels;

	public RewaField() {
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

	public List<RewaPersonLevel> getRewaPersonLevels() {
		return this.rewaPersonLevels;
	}

	public void setRewaPersonLevels(List<RewaPersonLevel> rewaPersonLevels) {
		this.rewaPersonLevels = rewaPersonLevels;
	}

	public RewaPersonLevel addRewaPersonLevel(RewaPersonLevel rewaPersonLevel) {
		getRewaPersonLevels().add(rewaPersonLevel);
		rewaPersonLevel.setRewaField(this);

		return rewaPersonLevel;
	}

	public RewaPersonLevel removeRewaPersonLevel(RewaPersonLevel rewaPersonLevel) {
		getRewaPersonLevels().remove(rewaPersonLevel);
		rewaPersonLevel.setRewaField(null);

		return rewaPersonLevel;
	}

}