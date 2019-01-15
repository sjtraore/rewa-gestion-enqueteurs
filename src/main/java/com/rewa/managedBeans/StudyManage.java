package com.rewa.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.rewa.beans.PersonBean;
import com.rewa.beans.StudyBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Customer;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Role;
import com.rewa.hibernate.data.Status;
import com.rewa.hibernate.data.Study;
import com.rewa.spring.service.CommonService;
import com.rewa.spring.service.CustomerService;
import com.rewa.spring.service.PersonService;
import com.rewa.spring.service.StudyService;
import com.rewa.utils.PersonUtils;
import com.rewa.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class StudyManage {
	private static final Logger log = Logger.getLogger(StudyManage.class);

	private List<StudyBean> studiesBean;
	
	@ManagedProperty("#{studyService}")
	private StudyService studyService;

	@ManagedProperty("#{commonService}")
	private CommonService commonService;

	@ManagedProperty("#{customerService}")
	private CustomerService customerService;

	@ManagedProperty("#{personService}")
	private PersonService personService;

	private StudyBean studyBean;
	private Study study;
	private Status status;

	//Key = fullname, Value = fullname
	private Map<String, String> customersStringMap;
	//Key = fullname, Value = fullname
	private Map<String, String> supervisorsStringMap;

	@PostConstruct
	public void init() {
		status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);
		studiesBean = getActiveStudies();
	}

	private void setSupervisorsMap() {
		Role supervisorRole = commonService.getRoleById(Constant.SUPERVISEUR_ROLE_ID);
		List<Person> supervisors = personService.getPersonsByRole(supervisorRole);
		Set<PersonBean> supervisorsBean = PersonUtils.getPersonBeanListFromPersonList(new HashSet<Person>(supervisors), true);
		if (supervisorsBean != null && !supervisorsBean.isEmpty()) {
			if(supervisorsStringMap == null) {
				supervisorsStringMap = new HashMap<String, String>();
			}
			for (PersonBean supervisorBean : supervisorsBean) {
				supervisorsStringMap.put(supervisorBean.getFullname(), supervisorBean.getFullname());
			}
		}

	}

	private void setCustomersMap() {
		List<Customer> customers = customerService.getCustomersByStatus(status);
		if (customers != null) {
			if(customersStringMap == null) {
				customersStringMap = new HashMap<String, String>();
			}
			for (Customer customer : customers) {
				customersStringMap.put(customer.getName(), customer.getName());
			}
		}
	}

	public String register() {
		int connectedUserId = SessionUtils.getConnectedPerson().getIdPerson();
		if(studyBean.getCreatedDate() == null) {
			studyBean.setCreatedDate(new Date());
			studyBean.setCreatorId(connectedUserId);;
		}
		studyBean.setModifiedDate(new Date());
		studyBean.setModifierId(connectedUserId);
		study = getStudyByStudyBean(studyBean);
		studyService.save(study);
		
		return Constant.VIEW_STUDIES_PAGE_OUTCOME;
	}

	private Study getStudyByStudyBean(StudyBean studyBean) {
		Study study = null;
		if(studyBean != null) {
			study = new Study();
			
			study.setIdStudy(studyBean.getId());
			study.setTitle(studyBean.getTitle());
			study.setCreateDate(studyBean.getCreatedDate());
			study.setModifiedDate(studyBean.getModifiedDate());
			
			Person creator = personService.getPersonById(studyBean.getCreatorId());
			study.setCreatedBy(creator);
			
			Person modifier = personService.getPersonById(studyBean.getModifierId());
			study.setModifiedBy(modifier);
			
			Customer customer = customerService.getCustomerByName(studyBean.getCustomer());
			study.setCustomer(customer);

			study.setStartDate(studyBean.getStartDate());
			study.setEndDate(studyBean.getEndDate());
			
			status = commonService.getStatusByStatusName(studyBean.getStatus());
			study.setStatus(status);
			
			//TODO set team
//			Team team = teamServervice.getTeamById(studyBean.getTeamId());
//			study.setTeam(team);
			
		}
		return study;
	}

	private List<StudyBean> getActiveStudies() {
		log.debug("Getting Active studies");
		List<StudyBean> result = null;
		if(status.getIdStatus() != Constant.ACTIVE_STATUS_ID) {
			status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);
		}
		List<Study> studies = studyService.getStudiesByStatus(status);
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
			studyBean.setCustomer(study.getCustomer().getName());
			studyBean.setStartDate(study.getStartDate());
			studyBean.setEndDate(study.getEndDate());
			
			if(study.getTeam() != null) {
				Person supervisor = study.getTeam().getSupervisor();
				if (supervisor != null) {
					studyBean.setSupervisor(supervisor.getFirstname() + " " + supervisor.getLastname());
				}
			}
		}
		return studyBean;
	}

	public void disableStudy(StudyBean studyBean) {
		Study study = studyService.getStudyById(studyBean.getId());
		Status inactiveStatus = commonService.getStatusByStatusName(Constant.INACTIVE_STATUS);
		study.setStatus(inactiveStatus);
		studyService.save(study);
	}

	public String forwardToAddStudy(StudyBean studyBean) {
		studyBean = (studyBean != null) ? studyBean : new StudyBean();
		this.studyBean = studyBean;
		setCustomersMap();
		setSupervisorsMap();
		return Constant.VIEW_ADD_STUDY_PAGE_OUTCOME;
	}
	
	public String forwardToManageCustomers() {
		return Constant.VIEW_CUSTOMERS_PAGE_OUTCOME;
	}

	public void delete(StudyBean studyBean) {
		Study study = studyService.getStudyById(studyBean.getId());
		studyService.delete(study);
	}
	

	public void closeStudy(StudyBean studyBean) {
		Study study = studyService.getStudyById(studyBean.getId());
		Status cosedStatus = commonService.getStatusByStatusId(Constant.INACTIVE_STATUS_ID);
		study.setStatus(cosedStatus);
		studyService.save(study);
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

	public StudyBean getStudyBean() {
		return studyBean;
	}

	public void setStudyBean(StudyBean studyBean) {
		this.studyBean = studyBean;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public Map<String, String> getCustomersStringMap() {
		return customersStringMap;
	}

	public void setCustomersStringMap(Map<String, String> customersStringMap) {
		this.customersStringMap = customersStringMap;
	}

	public Map<String, String> getSupervisorsStringMap() {
		return supervisorsStringMap;
	}

	public void setSupervisorsStringMap(Map<String, String> supervisorsStringMap) {
		this.supervisorsStringMap = supervisorsStringMap;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

}
