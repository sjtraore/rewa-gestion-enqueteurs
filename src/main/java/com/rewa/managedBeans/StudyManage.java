package com.rewa.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.rewa.beans.StudyBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Status;
import com.rewa.hibernate.data.Study;
import com.rewa.spring.service.CommonService;
import com.rewa.spring.service.StudyService;

@ManagedBean
@SessionScoped
public class StudyManage {
	private static final Logger log = Logger.getLogger(StudyManage.class);

	private List<StudyBean> studiesBean;
	@ManagedProperty("#{studyService}")
	private StudyService studyService;
	
	@ManagedProperty("#{commonService}")
	private CommonService commonService;
	
	private Status status;
	
	
	@PostConstruct
	public void init() {
		
	}
	
	public String saveStudy() {
		return "";
	}

	public List<StudyBean> getActiveStudies() {
		log.debug("Getting Active studies");
		List<StudyBean> result = null;
		List<Study> studies = studyService.getActiveStudies(status);
		if (studies != null && !studies.isEmpty()) {
			result = new ArrayList<StudyBean>();
			for (Study study : studies) {
				result.add(getStudyBeanFromStudy(study));
			}
		}
		return result;
	}

	private StudyBean getStudyBeanFromStudy(Study study) {
		StudyBean studyBean = null;
		if (study != null) {
			studyBean = new StudyBean();
			studyBean.setId(study.getIdStudy());
			studyBean.setTitle(study.getTitle());
			studyBean.setStatus(study.getStatus().getStatus());
			studyBean.setCustomer(study.getRewaCustomer().getName());
			studyBean.setStartDate(study.getStartDate());
			studyBean.setEndDate(study.getEndDate());
			
			Person supervisor = study.getRewaTeam().getSupervisor();
			if (supervisor != null) {
				studyBean.setSupervisor(supervisor.getFirstname() + " " 
						+ supervisor.getLastname());
			}
		}
		return studyBean;
	}
	
	public void softDeleteStudy(StudyBean studyBean) {
		Study study = studyService.getStudyById(studyBean.getId());
		Status inactiveStatus = commonService.getStatusByStatusName(Constant.INACTIVE_STATUS);
		study.setStatus(inactiveStatus);
	}

	public List<StudyBean> getStudiesBean() {
		return studiesBean;
	}

	public void setStudiesBean(List<StudyBean> studiesBean) {
		this.studiesBean = studiesBean;
	}

	public StudyService getStudyService() {
		return studyService;
	}

	public void setStudyService(StudyService studyService) {
		this.studyService = studyService;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
}
