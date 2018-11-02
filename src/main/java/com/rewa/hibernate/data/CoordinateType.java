package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the rewa_coordinate_type database table.
 * 
 */
@Entity
@Table(name = "rewa_coordinate_type")
@NamedQuery(name = "CoordinateType.findAll", query = "SELECT r FROM CoordinateType r")
public class CoordinateType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCoordinateType;
	private String type;

	public int getIdCoordinateType() {
		return idCoordinateType;
	}

	public void setIdCoordinateType(int idCoordinateType) {
		this.idCoordinateType = idCoordinateType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCoordinateType;
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
		CoordinateType other = (CoordinateType) obj;
		if (idCoordinateType != other.idCoordinateType)
			return false;
		return true;
	}

}