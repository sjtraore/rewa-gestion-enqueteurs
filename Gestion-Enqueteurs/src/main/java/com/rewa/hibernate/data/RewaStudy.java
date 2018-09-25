package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rewa_study database table.
 * 
 */
@Entity
@Table(name="rewa_study")
@NamedQuery(name="RewaStudy.findAll", query="SELECT r FROM RewaStudy r")
public class RewaStudy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStudy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to RewaCustomer
	@ManyToOne
	@JoinColumn(name="idCustomer")
	private RewaCustomer rewaCustomer;

	//bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name="idStatus")
	private RewaStatus rewaStatus;

	//bi-directional many-to-one association to RewaTeam
	@ManyToOne
	@JoinColumn(name="idTeam")
	private RewaTeam rewaTeam;

	public RewaStudy() {
	}

	public int getIdStudy() {
		return this.idStudy;
	}

	public void setIdStudy(int idStudy) {
		this.idStudy = idStudy;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public RewaCustomer getRewaCustomer() {
		return this.rewaCustomer;
	}

	public void setRewaCustomer(RewaCustomer rewaCustomer) {
		this.rewaCustomer = rewaCustomer;
	}

	public RewaStatus getRewaStatus() {
		return this.rewaStatus;
	}

	public void setRewaStatus(RewaStatus rewaStatus) {
		this.rewaStatus = rewaStatus;
	}

	public RewaTeam getRewaTeam() {
		return this.rewaTeam;
	}

	public void setRewaTeam(RewaTeam rewaTeam) {
		this.rewaTeam = rewaTeam;
	}

}