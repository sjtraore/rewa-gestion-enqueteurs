package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rewa_person_level database table.
 * 
 */
@Embeddable
public class RewaPersonLevelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPerson;

	@Column(insertable=false, updatable=false)
	private int idField;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dateLevel;

	public RewaPersonLevelPK() {
	}
	public int getIdPerson() {
		return this.idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public int getIdField() {
		return this.idField;
	}
	public void setIdField(int idField) {
		this.idField = idField;
	}
	public java.util.Date getDateLevel() {
		return this.dateLevel;
	}
	public void setDateLevel(java.util.Date dateLevel) {
		this.dateLevel = dateLevel;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RewaPersonLevelPK)) {
			return false;
		}
		RewaPersonLevelPK castOther = (RewaPersonLevelPK)other;
		return 
			(this.idPerson == castOther.idPerson)
			&& (this.idField == castOther.idField)
			&& this.dateLevel.equals(castOther.dateLevel);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPerson;
		hash = hash * prime + this.idField;
		hash = hash * prime + this.dateLevel.hashCode();
		
		return hash;
	}
}