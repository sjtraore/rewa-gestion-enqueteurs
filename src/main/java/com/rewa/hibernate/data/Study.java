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
@NamedQuery(name="Study.findAll", query="SELECT r FROM Study r")
public class Study implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStudy;
	
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	//bi-directional many-to-one association to RewaCustomer
	@ManyToOne
	@JoinColumn(name="idCustomer")
	private Customer rewaCustomer;

	//bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name="idStatus")
	private Status status;

	//bi-directional many-to-one association to RewaTeam
	@ManyToOne
	@JoinColumn(name="idTeam")
	private Team rewaTeam;

	public Study() {
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

	public Customer getRewaCustomer() {
		return this.rewaCustomer;
	}

	public void setRewaCustomer(Customer rewaCustomer) {
		this.rewaCustomer = rewaCustomer;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Team getRewaTeam() {
		return this.rewaTeam;
	}

	public void setRewaTeam(Team rewaTeam) {
		this.rewaTeam = rewaTeam;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Study other = (Study) obj;
		if (idStudy != other.idStudy)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Study [idStudy=" + idStudy + ", title=" + title + ", endDate=" + endDate + ", startDate=" + startDate
				+ "]";
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}