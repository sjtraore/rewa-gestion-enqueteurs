package com.rewa.hibernate.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="rewa_coordinate")
@NamedQuery(name="Coordinate.findAll", query="SELECT r FROM Coordinate r")
public class Coordinate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idcoordinate;
	
	private String coordinate;
	private CoordinateType coordinateTypeId;
	
	//bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;

	//bi-directional many-to-one association to RewaPerson
	@ManyToOne
	@JoinColumn(name="idPerson")
	private Person owner;
	
	private int priority;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	private Person createdBy;
	private Person modifiedBy;
	
	private Customer customers;

	public long getIdcoordinate() {
		return idcoordinate;
	}

	public void setIdcoordinate(long idcoordinate) {
		this.idcoordinate = idcoordinate;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public CoordinateType getCoordinateTypeId() {
		return coordinateTypeId;
	}

	public void setCoordinateTypeId(CoordinateType coordinateTypeId) {
		this.coordinateTypeId = coordinateTypeId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Person getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Person createdBy) {
		this.createdBy = createdBy;
	}

	public Person getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Person modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Customer getCustomer() {
		return customers;
	}

	public void setCustomer(Customer customer) {
		this.customers = customer;
	}

}
