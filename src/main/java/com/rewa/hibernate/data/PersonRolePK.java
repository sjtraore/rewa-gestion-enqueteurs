package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rewa_person_role database table.
 * 
 */
@Embeddable
public class PersonRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, insertable=false, updatable=false)
	private int idPerson;

	@Column(nullable = false, insertable=false, updatable=false)
	private int idRole;

	public PersonRolePK() {
	}
	public int getIdPerson() {
		return this.idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public int getIdRole() {
		return this.idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonRolePK)) {
			return false;
		}
		PersonRolePK castOther = (PersonRolePK)other;
		return 
			(this.idPerson == castOther.idPerson)
			&& (this.idRole == castOther.idRole);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPerson;
		hash = hash * prime + this.idRole;
		
		return hash;
	}
}