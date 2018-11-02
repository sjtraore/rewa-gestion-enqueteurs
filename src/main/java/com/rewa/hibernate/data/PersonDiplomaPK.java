package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rewa_person_role database table.
 * 
 */
@Embeddable
public class PersonDiplomaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, insertable=false, updatable=false)
	private int idPerson;

	@Column(nullable = false, insertable=false, updatable=false)
	private int idDiploma;

	public PersonDiplomaPK() {
	}
	public int getIdPerson() {
		return this.idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public int getIdDiploma() {
		return idDiploma;
	}
	public void setIdDiploma(int idDiploma) {
		this.idDiploma = idDiploma;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDiploma;
		result = prime * result + idPerson;
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
		PersonDiplomaPK other = (PersonDiplomaPK) obj;
		if (idDiploma != other.idDiploma)
			return false;
		if (idPerson != other.idPerson)
			return false;
		return true;
	}


}