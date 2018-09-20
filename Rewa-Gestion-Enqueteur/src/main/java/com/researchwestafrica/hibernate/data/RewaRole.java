package com.researchwestafrica.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rewa_role database table.
 * 
 */
@Entity
@Table(name="rewa_role")
@NamedQuery(name="RewaRole.findAll", query="SELECT r FROM RewaRole r")
public class RewaRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRole;

	private String role;

	//bi-directional many-to-one association to RewaPersonRole
	@OneToMany(mappedBy="rewaRole")
	private List<RewaPersonRole> rewaPersonRoles;

	public RewaRole() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<RewaPersonRole> getRewaPersonRoles() {
		return this.rewaPersonRoles;
	}

	public void setRewaPersonRoles(List<RewaPersonRole> rewaPersonRoles) {
		this.rewaPersonRoles = rewaPersonRoles;
	}

	public RewaPersonRole addRewaPersonRole(RewaPersonRole rewaPersonRole) {
		getRewaPersonRoles().add(rewaPersonRole);
		rewaPersonRole.setRewaRole(this);

		return rewaPersonRole;
	}

	public RewaPersonRole removeRewaPersonRole(RewaPersonRole rewaPersonRole) {
		getRewaPersonRoles().remove(rewaPersonRole);
		rewaPersonRole.setRewaRole(null);

		return rewaPersonRole;
	}

}