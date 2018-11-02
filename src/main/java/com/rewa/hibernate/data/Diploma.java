package com.rewa.hibernate.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the rewa_status database table.
 * 
 */
@Entity
@Table(name="rewa_diploma")
@NamedQuery(name="Diploma.findAll", query="SELECT r FROM Diploma r")
public class Diploma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDiploma;
	private String diploma;

	public Diploma() {
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDiploma;
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
		Diploma other = (Diploma) obj;
		if (idDiploma != other.idDiploma)
			return false;
		return true;
	}


	public int getIdDiploma() {
		return idDiploma;
	}


	public void setIdDiploma(int idDiploma) {
		this.idDiploma = idDiploma;
	}


	public String getDiploma() {
		return diploma;
	}


	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

}