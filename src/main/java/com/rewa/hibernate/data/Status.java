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
@Table(name="rewa_status")
@NamedQuery(name="Status.findAll", query="SELECT r FROM Status r")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idStatus;
	private String status;

	public Status() {
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idStatus;
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
		Status other = (Status) obj;
		if (idStatus != other.idStatus)
			return false;
		return true;
	}

}