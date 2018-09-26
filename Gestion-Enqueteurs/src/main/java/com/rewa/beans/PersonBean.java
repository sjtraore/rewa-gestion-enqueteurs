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

	private String creatorName;
	private String updatorName;
	private String rewaStatus;
	
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
	public String getRewaStatus() {
		return rewaStatus;
	}
	public void setRewaStatus(String rewaStatus) {
		this.rewaStatus = rewaStatus;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
}
