package com.researchwestafrica.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the rewa_coordinate_type database table.
 * 
 */
@Embeddable
public class RewaCoordinateTypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idCoordinateType;

	private String type;

	public RewaCoordinateTypePK() {
	}
	public int getIdCoordinateType() {
		return this.idCoordinateType;
	}
	public void setIdCoordinateType(int idCoordinateType) {
		this.idCoordinateType = idCoordinateType;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RewaCoordinateTypePK)) {
			return false;
		}
		RewaCoordinateTypePK castOther = (RewaCoordinateTypePK)other;
		return 
			(this.idCoordinateType == castOther.idCoordinateType)
			&& this.type.equals(castOther.type);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCoordinateType;
		hash = hash * prime + this.type.hashCode();
		
		return hash;
	}
}