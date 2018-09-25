package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_coordinate_type database table.
 * 
 */
@Entity
@Table(name="rewa_coordinate_type")
@NamedQuery(name="RewaCoordinateType.findAll", query="SELECT r FROM RewaCoordinateType r")
public class RewaCoordinateType implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RewaCoordinateTypePK id;

	public RewaCoordinateType() {
	}

	public RewaCoordinateTypePK getId() {
		return this.id;
	}

	public void setId(RewaCoordinateTypePK id) {
		this.id = id;
	}

}