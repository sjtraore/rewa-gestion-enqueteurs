package com.rewa.beans;

import java.util.Date;
import java.util.List;

public class TeamBean {
	private int idTeam;
	private int validated;
	private int studyId;
	private int studyName;
	private String supervisorName;
	private int supervisorId;
	private List<PersonBean> enqueteurs;
	private String createdBy;
	private int creatorId;
	private String modifiedBy;
	private int modifierId;
	private Date createdDate;
	private Date modifiedDate;
	
	public TeamBean() {
		super();
	}
	
	public int getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}
	public int getValidated() {
		return validated;
	}
	public void setValidated(int validated) {
		this.validated = validated;
	}
	public int getStudyId() {
		return studyId;
	}
	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}
	public int getStudyName() {
		return studyName;
	}
	public void setStudyName(int studyName) {
		this.studyName = studyName;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	public List<PersonBean> getEnqueteurs() {
		return enqueteurs;
	}
	public void setEnqueteurs(List<PersonBean> enqueteurs) {
		this.enqueteurs = enqueteurs;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public int getModifierId() {
		return modifierId;
	}

	public void setModifierId(int modifierId) {
		this.modifierId = modifierId;
	}
}
