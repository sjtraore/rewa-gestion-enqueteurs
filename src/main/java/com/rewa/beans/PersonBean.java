package com.rewa.beans;

import java.util.Date;

public class PersonBean {
	private int idPerson;
	private String firstname;
	private String lastname;
	private String fullname;
	private Date createdDate;
	private Date modifiedDate;
	private String password;
	private byte[] picture;

	private long creatorId;
	private long updatorId;
	private String creatorName;
	private String updatorName;
	private String status;
	
	/********** Coordinate ***********/
	private String primaryPhone;
	private String secondaryPhone;
	private String primaryEmail;
	private String secondaryEmail;
	private String address;
	private String facebook;
	private String region;
	private String contry;
	
	/********** Notation by role ***********/
	private double enqueteurAverage;
	private double controlleurAverage;
	
	
	public PersonBean() {
		super();
		this.createdDate = new Date();
		this.modifiedDate = new Date();
	}
	
	public PersonBean(int idPerson, String firstname, String lastname, String fullname, Date createdDate,
			Date modifiedDate, String password, long creatorId, long updatorId, String creatorName,
			String updatorName, String status) {
		super();
		this.idPerson = idPerson;
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = fullname;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.password = password;
		this.creatorId = creatorId;
		this.updatorId = updatorId;
		this.creatorName = creatorName;
		this.updatorName = updatorName;
		this.status = status;
	}
	
	public PersonBean(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.createdDate = new Date();
		this.modifiedDate = new Date();
	}

	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idAgent) {
		this.idPerson = idAgent;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getUpdatorName() {
		return updatorName;
	}
	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}
	public long getUpdatorId() {
		return updatorId;
	}
	public void setUpdatorId(long updatorId) {
		this.updatorId = updatorId;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
	}

	public double getEnqueteurAverage() {
		return enqueteurAverage;
	}

	public void setEnqueteurAverage(double enqueteurAverage) {
		this.enqueteurAverage = enqueteurAverage;
	}

	public double getControlleurAverage() {
		return controlleurAverage;
	}

	public void setControlleurAverage(double controlleurAverage) {
		this.controlleurAverage = controlleurAverage;
	}

	public void setstatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PersonBean [idPerson=" + idPerson + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
}
