package com.rewa.beans;

import java.util.Date;

public class StudyBean {
	private int id;
	private String title;
	private String customer;
	private String supervisor;
	private Date startDate;
	private Date endDate;
	private String status;
	
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

}
