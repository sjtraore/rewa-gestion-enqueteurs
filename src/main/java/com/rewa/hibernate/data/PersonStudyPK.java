package com.rewa.hibernate.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonStudyPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "idPerson", nullable = false, insertable=false, updatable=false)
	private int idPerson;

	@Column(name = "idStudy", nullable = false, insertable=false, updatable=false)
	private int idStudy;

	public PersonStudyPK() {
		super();
	}
	
	public PersonStudyPK(int idPerson, int idStudy) {
		super();
		this.idPerson = idPerson;
		this.idStudy = idStudy;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public int getIdStudy() {
		return idStudy;
	}

	public void setIdStudy(int idStudy) {
		this.idStudy = idStudy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPerson;
		result = prime * result + idStudy;
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
		PersonStudyPK other = (PersonStudyPK) obj;
		if (idPerson != other.idPerson)
			return false;
		if (idStudy != other.idStudy)
			return false;
		return true;
	}
}
