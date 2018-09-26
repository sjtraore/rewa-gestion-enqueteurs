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
	@OneToMany(mappedBy="rewaTeam")
	private List<Study> rewaStudies;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="idSupervisor")
	private Person rewaPerson;

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

	public List<Study> getRewaStudies() {
		return this.rewaStudies;
	}

	public void setRewaStudies(List<Study> rewaStudies) {
		this.rewaStudies = rewaStudies;
	}

	public Study addRewaStudy(Study rewaStudy) {
		getRewaStudies().add(rewaStudy);
		rewaStudy.setRewaTeam(this);

		return rewaStudy;
	}

	public Study removeRewaStudy(Study rewaStudy) {
		getRewaStudies().remove(rewaStudy);
		rewaStudy.setRewaTeam(null);

		return rewaStudy;
	}

	public Person getRewaPerson() {
		return this.rewaPerson;
	}

	public void setRewaPerson(Person rewaPerson) {
		this.rewaPerson = rewaPerson;
	}

}