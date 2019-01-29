package com.rewa.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class PersonBean {
	private int idPerson;
	private String firstname;
	private String lastname;
	private String fullname;
	private Date createdDate;
	private Date modifiedDate;
	private String username;
	private String password;
	private byte[] picture;

	private long creatorId;
	private long updatorId;
	private String creatorName;
	private String updatorName;
	private String status;
	
	/********** Current Coordinates ***********/
	private String primaryPhone;
	private String secondaryPhone;
	private String primaryEmail;
	private String secondaryEmail;
	private String address;
	private String facebook;
	private String region;
	private String contry;
	
	/********** Roles ***********/
	private List<String> roles;
	
	/********** Notation by role ***********/
	private double enqueteurAverage;
	private double controlleurAverage;
	
	/********** Language rating ***********/
	private Integer ratingFrenchOral;
	private Integer ratingFrenchWriting;
	private Integer ratingLocalLanguage;
	private String localLanguages;
	
	private Integer ratingMSWord;
	private Integer ratingMSExcel;
	private Integer ratingMSPowerpoint;
	private BigDecimal averageEnq;
	private BigDecimal averageCont;
	
	private BigDecimal avgPunctuality;
	private BigDecimal avgDiligence;
	
	private String[] selectedDiplomas = null;

	private String[] selectedRoles = null;
	
	public PersonBean() {
		super();
		this.createdDate = new Date();
		this.modifiedDate = new Date();
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getRatingFrenchOral() {
		return ratingFrenchOral;
	}


	public void setRatingFrenchOral(Integer ratingFrenchOral) {
		this.ratingFrenchOral = ratingFrenchOral;
	}


	public Integer getRatingFrenchWriting() {
		return ratingFrenchWriting;
	}


	public void setRatingFrenchWriting(Integer ratingFrenchWriting) {
		this.ratingFrenchWriting = ratingFrenchWriting;
	}


	public Integer getRatingLocalLanguage() {
		return ratingLocalLanguage;
	}


	public void setRatingLocalLanguage(Integer ratingLocalLanguage) {
		this.ratingLocalLanguage = ratingLocalLanguage;
	}


	public String getLocalLanguages() {
		return localLanguages;
	}


	public void setLocalLanguages(String localLanguages) {
		this.localLanguages = localLanguages;
	}


	public Integer getRatingMSWord() {
		return ratingMSWord;
	}


	public void setRatingMSWord(Integer ratingMSWord) {
		this.ratingMSWord = ratingMSWord;
	}


	public Integer getRatingMSExcel() {
		return ratingMSExcel;
	}


	public void setRatingMSExcel(Integer ratingMSExcel) {
		this.ratingMSExcel = ratingMSExcel;
	}


	public Integer getRatingMSPowerpoint() {
		return ratingMSPowerpoint;
	}


	public void setRatingMSPowerpoint(Integer ratingMSPowerpoint) {
		this.ratingMSPowerpoint = ratingMSPowerpoint;
	}


	public BigDecimal getAverageCont() {
		return averageCont;
	}


	public void setAverageCont(BigDecimal averageCont) {
		this.averageCont = averageCont;
	}


	public BigDecimal getAverageEnq() {
		return averageEnq;
	}


	public void setAverageEnq(BigDecimal averageEnq) {
		this.averageEnq = averageEnq;
	}


	public String[] getSelectedDiplomas() {
		return selectedDiplomas;
	}


	public void setSelectedDiplomas(String[] selectedDiplomas) {
		this.selectedDiplomas = selectedDiplomas;
	}


	public String[] getSelectedRoles() {
		return selectedRoles;
	}


	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPerson;
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
		PersonBean other = (PersonBean) obj;
		if (idPerson != other.idPerson)
			return false;
		return true;
	}


	public BigDecimal getAvgPunctuality() {
		return avgPunctuality;
	}


	public void setAvgPunctuality(BigDecimal avgPunctuality) {
		this.avgPunctuality = avgPunctuality;
	}


	public BigDecimal getAvgDiligence() {
		return avgDiligence;
	}


	public void setAvgDiligence(BigDecimal avgDiligence) {
		this.avgDiligence = avgDiligence;
	}
	
}
