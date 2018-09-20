package com.researchwestafrica.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_person_role database table.
 * 
 */
@Entity
@Table(name="rewa_person_role")
@NamedQuery(name="RewaPersonRole.findAll", query="SELECT r FROM RewaPersonRole r")
public class RewaPersonRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RewaPersonRolePK id;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="idPerson")
	private RewaPerson rewaPerson;

	//bi-directional many-to-one association to RewaRole
	@ManyToOne
	@JoinColumn(name="idRole")
	private RewaRole rewaRole;

	//bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name="idStatus")
	private RewaStatus rewaStatus;

	public RewaPersonRole() {
	}

	public RewaPersonRolePK getId() {
		return this.id;
	}

	public void setId(RewaPersonRolePK id) {
		this.id = id;
	}

	public RewaPerson getRewaPerson() {
		return this.rewaPerson;
	}

	public void setRewaPerson(RewaPerson rewaPerson) {
		this.rewaPerson = rewaPerson;
	}

	public RewaRole getRewaRole() {
		return this.rewaRole;
	}

	public void setRewaRole(RewaRole rewaRole) {
		this.rewaRole = rewaRole;
	}

	public RewaStatus getRewaStatus() {
		return this.rewaStatus;
	}

	public void setRewaStatus(RewaStatus rewaStatus) {
		this.rewaStatus = rewaStatus;
	}

}