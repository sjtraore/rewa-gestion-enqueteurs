package com.rewa.hibernate.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "rewa_person_study")
@NamedQueries({
	@NamedQuery(name = "PersonStudy.findAll", query = "SELECT r FROM PersonStudy r"),
	@NamedQuery(name = "PersonStudy.findByStudy", query = "SELECT r FROM PersonStudy r where r.study = :study"),
	@NamedQuery(name = "PersonStudy.findByPerson", query = "SELECT r FROM PersonStudy r where r.person = :person"),
	@NamedQuery(name = "PersonStudy.findByPersonAndStudy", query = "SELECT r FROM PersonStudy r "
			+ "where r.study = :study and r.person = :person"),
	@NamedQuery(name = "PersonStudy.findByPersonAndStudyStatus", query = "SELECT r FROM PersonStudy r left join fetch r.study s "
			+ "where r.person = :person and s.status = :studyStatus")
})
public class PersonStudy implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersonStudy;

	@ManyToOne
	@JoinColumn(name = "idPerson")
	private Person person;

	@ManyToOne
	@JoinColumn(name = "idStudy")
	private Study study;

	public PersonStudy() {
		super();
	}
	
	public PersonStudy(Person person, Study study) {
		super();
		this.person = person;
		this.study = study;
	}

	private BigDecimal markPunctuality;

	private BigDecimal markDiligence;


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public BigDecimal getMarkPunctuality() {
		return markPunctuality;
	}

	public void setMarkPunctuality(BigDecimal markPunctuality) {
		this.markPunctuality = markPunctuality;
	}

	public BigDecimal getMarkDiligence() {
		return markDiligence;
	}

	public void setMarkDiligence(BigDecimal markDiligence) {
		this.markDiligence = markDiligence;
	}

	public int getIdPersonStudy() {
		return idPersonStudy;
	}

	public void setIdPersonStudy(int idPersonStudy) {
		this.idPersonStudy = idPersonStudy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPersonStudy;
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
		PersonStudy other = (PersonStudy) obj;
		if (idPersonStudy != other.idPersonStudy)
			return false;
		return true;
	}
}
