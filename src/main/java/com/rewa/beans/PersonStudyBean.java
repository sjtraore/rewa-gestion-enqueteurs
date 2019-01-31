package com.rewa.beans;

public class PersonStudyBean {
	private int idPersonStudy;
	private PersonBean personBean;
	private StudyBean studyBean;
	private Integer markPunctuality;
	private Integer markDiligence;
	
	
	public PersonStudyBean() {
		super();
	}
	public int getIdPersonStudy() {
		return idPersonStudy;
	}
	public void setIdPersonStudy(int idPersonStudy) {
		this.idPersonStudy = idPersonStudy;
	}
	public PersonBean getPersonBean() {
		return personBean;
	}
	public void setPersonBean(PersonBean personBean) {
		this.personBean = personBean;
	}
	public StudyBean getStudyBean() {
		return studyBean;
	}
	public void setStudyBean(StudyBean studyBean) {
		this.studyBean = studyBean;
	}
	public Integer getMarkPunctuality() {
		return markPunctuality;
	}
	public void setMarkPunctuality(Integer markPunctuality) {
		this.markPunctuality = markPunctuality;
	}
	public Integer getMarkDiligence() {
		return markDiligence;
	}
	public void setMarkDiligence(Integer markDiligence) {
		this.markDiligence = markDiligence;
	}
}
