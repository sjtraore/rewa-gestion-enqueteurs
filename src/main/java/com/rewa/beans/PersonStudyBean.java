package com.rewa.beans;

import java.math.BigDecimal;

public class PersonStudyBean {
	private int idPersonStudy;
	private PersonBean personBean;
	private StudyBean studyBean;
	private BigDecimal markPunctuality;
	private BigDecimal markDiligence;
	
	
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
	public BigDecimal getMarkPunctuality() {
		return markPunctuality;
	}
	public void setMarkPunctuality(BigDecimal markPunctuality) {
		this.markPunctuality = markPunctuality;
	}
	public BigDecimal getMarkDiligence() {
		return markDiligence;
	}
	public void setMarkDiligence(BigDecimal markDiligence) {
		this.markDiligence = markDiligence;
	}
}
