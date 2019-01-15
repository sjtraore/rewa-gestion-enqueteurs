package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rewa_team database table.
 * 
 */
@Entity
@Table(name="rewa_team")
@NamedQuery(name="Team.findAll", query="SELECT r FROM Team r")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTeam;

	private int validated;

	//bi-directional many-to-one association to RewaStudy
	@OneToMany(mappedBy="team")
	private List<Study> studies;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="idSupervisor")
	private Person supervisor;

	public Team() {
	}

	public int getIdTeam() {
		return this.idTeam;
	}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}

	public int getValidated() {
		return this.validated;
	}

	public void setValidated(int validated) {
		this.validated = validated;
	}

	public List<Study> getStudies() {
		return this.studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public Study addStudy(Study study) {
		getStudies().add(study);
		study.setTeam(this);

		return study;
	}

	public Study removeStudy(Study study) {
		getStudies().remove(study);
		study.setTeam(null);

		return study;
	}

	public Person getSupervisor() {
		return this.supervisor;
	}

	public void setSupervisor(Person supervisor) {
		this.supervisor = supervisor;
	}

}