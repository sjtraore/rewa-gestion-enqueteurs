package com.rewa.hibernate.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
	@ManyToOne
	@JoinColumn(name="coordinateTypeId")
	private CoordinateType type;
	
	//bi-directional many-to-one association to RewaStatus
	@ManyToOne
	@JoinColumn(name="statusId")
	private Status status;

	//bi-directional many-to-one association to Person
	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="personId")
	private Person owner;
	
	private int priority;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	@ManyToOne
	@JoinColumn(name="createdBy")
	private Person createdBy;
	
	@ManyToOne
	@JoinColumn(name="modifiedBy")
	private Person modifiedBy;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

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

	public CoordinateType getType() {
		return type;
	}

	public void setType(CoordinateType type) {
		this.type = type;
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
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idcoordinate ^ (idcoordinate >>> 32));
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
		Coordinate other = (Coordinate) obj;
		if (idcoordinate != other.idcoordinate)
			return false;
		return true;
	}

}
