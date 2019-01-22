package com.rewa.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudyBean {
	private int id;
	private String title;
	private String customer;
	private String supervisor;
	private int supervisorId;
	private Date startDate;
	private Date endDate;
	private String status;
	private Date createdDate;
	private String createdBy;
	private int creatorId;
	private Date modifiedDate;
	private String modifiedBy;
	private int modifierId;
	private String teamName;
	private int teamId;
	private List<PersonBean> enqueteurs;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		StudyBean other = (StudyBean) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StudyBean [id=" + id + ", title=" + title + ", customer=" + customer + ", supervisor=" + supervisor
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public int getModifierId() {
		return modifierId;
	}
	public void setModifierId(int modifierId) {
		this.modifierId = modifierId;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public List<PersonBean> getEnqueteurs() {
		return enqueteurs;
	}
	public void setEnqueteurs(List<PersonBean> enqueteurs) {
		this.enqueteurs = enqueteurs;
	}
	
	public PersonBean addEnqueteur(PersonBean enqueteurBean) {
		if (getEnqueteurs() == null) {
			setEnqueteurs(new ArrayList<PersonBean>());
		}
		getEnqueteurs().add(enqueteurBean);
		return enqueteurBean;
	}

	public PersonBean removePerson(PersonBean enqueteurBean) {
		getEnqueteurs().remove(enqueteurBean);
		return enqueteurBean;
	}
	
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

}
