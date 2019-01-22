package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class PersonStudyPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, insertable = false, updatable = false)
	private int idPerson;

	@Column(nullable = false, insertable = false, updatable = false)
	private int idStudy;

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
}
