package com.rewa.hibernate.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rewa_coordinate_type database table.
 * 
 */
@Entity
@Table(name="rewa_coordinate_type")
@NamedQuery(name="CoordinateType.findAll", query="SELECT r FROM CoordinateType r")
public class CoordinateType implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CoordinateTypePK id;

	public CoordinateType() {
	}

	public CoordinateTypePK getId() {
		return this.id;
	}

	public void setId(CoordinateTypePK id) {
		this.id = id;
	}

}