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

}