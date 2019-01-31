package com.rewa.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import com.rewa.beans.PersonBean;
import com.rewa.beans.PersonStudyBean;
import com.rewa.beans.StudyBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Customer;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.PersonStudy;
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
	private Set<PersonStudyBean> personStudiesBean;

	// Key = fullname, Value = fullname
	private Map<String, String> customersStringMap;

	// Key = fullname, Value = fullname
	private Map<String, Integer> selectedAgentsStringMap;
	private Map<String, Integer> availableAgentsStringMap;
	private int selectedAgentIdToBeAddedToTeam;

	// Key = fullname, Value = fullname
	private Map<String, Integer> supervisorsStringMap;

	@PostConstruct
	public void init() {
		status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);
		studiesBean = getActiveStudies();
	}

	public void setPersonStudiesBeanSetFromDB() {
		Set<PersonStudy> personStudySet = studyService.getPersonStudyListByStudy(study);
		if (personStudySet != null && !personStudySet.isEmpty()) {
			for (PersonStudy ps : personStudySet) {
				PersonStudyBean psBean = new PersonStudyBean();
				psBean.setIdPersonStudy(ps.getIdPersonStudy());
				psBean.setPersonBean(PersonUtils.getPersonBeanByPerson(ps.getPerson(), true));
				psBean.setStudyBean(getStudyBean());
				psBean.setMarkDiligence(ps.getMarkDiligence());
				psBean.setMarkPunctuality(ps.getMarkPunctuality());

				personStudiesBean.add(psBean);
			}
		}
	}

	/**
	 * Setting PersonStudy DB information from the table of enqueteurs after
	 * notation
	 */
	private void setPersonStudySetDBFromPSSetBean() {
		if (personStudiesBean != null && !personStudiesBean.isEmpty()) {
			for (PersonStudyBean psb : personStudiesBean) {
				int idPersonStudy = psb.getIdPersonStudy();
				PersonStudy ps = null;

				if (idPersonStudy != 0) {
					// Row exists in DB
					ps = studyService.getPersonStudyById(idPersonStudy);
				} else {
					ps = new PersonStudy(personService.getPersonById(psb.getPersonBean().getIdPerson()), study);
				}
				ps.setMarkPunctuality(psb.getMarkPunctuality());
				ps.setMarkDiligence(psb.getMarkDiligence());

				studyService.savePersonStudy(ps);
			}
		}
	}

	public void addAgentToTeam(int enqueteurId) {
		Person selectedPerson = personService.getPersonById(selectedAgentIdToBeAddedToTeam);
		if (selectedPerson != null) {
			PersonBean selectedPersonBean = PersonUtils.getPersonBeanByPerson(selectedPerson, true);
			studyBean.addEnqueteur(selectedPersonBean);
			if (selectedAgentsStringMap == null) {
				selectedAgentsStringMap = new HashMap<>();
			}
			selectedAgentsStringMap.put(selectedPersonBean.getFullname(), selectedPersonBean.getIdPerson());
			// Remove selected agents from availables
			availableAgentsStringMap.entrySet().removeAll(selectedAgentsStringMap.entrySet());

			// Add message
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Agent " + selectedPersonBean.getFullname() + " ajouté à l'équipe"));
			log.debug("New person added to team: " + selectedPerson.getIdPerson());
		}
	}

	public void removeAgentFromTeam(int enqueteurId) {
		Person selectedPerson = personService.getPersonById(enqueteurId);
		if (selectedPerson != null) {
			PersonBean selectedPersonBean = PersonUtils.getPersonBeanByPerson(selectedPerson, true);
			selectedPersonBean = studyBean.removeEnqueteur(selectedPersonBean);
			studyBean.removeEnqueteur(selectedPersonBean);
			// Remove selected agents from availables
			availableAgentsStringMap.put(selectedPersonBean.getFullname(), selectedPersonBean.getIdPerson());

			// Add message
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Agent " + selectedPersonBean.getFullname() + " retiré de l'équipe"));
			log.debug("Agent removed from team: " + selectedPerson.getIdPerson());
		}
	}

	private void setSupervisorsMap() {
		Role supervisorRole = commonService.getRoleById(Constant.SUPERVISEUR_ROLE_ID);
		List<Person> supervisors = personService.getPersonsByRole(supervisorRole);
		Set<PersonBean> supervisorsBean = PersonUtils.getPersonBeanListFromPersonList(new HashSet<Person>(supervisors),
				true);
		if (supervisorsBean != null && !supervisorsBean.isEmpty()) {
			if (supervisorsStringMap == null) {
				supervisorsStringMap = new HashMap<String, Integer>();
			}
			for (PersonBean supervisorBean : supervisorsBean) {
				supervisorsStringMap.put(supervisorBean.getFullname(), supervisorBean.getIdPerson());
			}
		}

	}

	private void setCustomersMap() {
		List<Customer> customers = customerService.getCustomersByStatus(status);
		if (customers != null) {
			if (customersStringMap == null) {
				customersStringMap = new HashMap<String, String>();
			}
			for (Customer customer : customers) {
				customersStringMap.put(customer.getName(), customer.getName());
			}
		}
	}

	public String register() {
		int connectedUserId = getConnectedUser().getIdPerson();
		if (studyBean.getCreatedDate() == null) {
			studyBean.setCreatedDate(new Date());
			studyBean.setCreatorId(connectedUserId);
		}
		studyBean.setModifiedDate(new Date());
		studyBean.setModifierId(connectedUserId);
		study = getStudyByStudyBean(studyBean);
		studyService.save(study);
		savePersonStudy();

		return Constant.VIEW_STUDIES_PAGE_OUTCOME;
	}

	private void savePersonStudy() {
		// Wether list of PersonStudy in DB is the same with the bean
		boolean dbPersonStudiesMatchSelectedPS = false;
		Set<PersonStudy> dbPersonStudies = studyService.getPersonStudyListByStudy(study);
		Set<PersonBean> pbDBList = new HashSet<PersonBean>();
		if (dbPersonStudies != null && !dbPersonStudies.isEmpty()) {
			for (PersonStudy ps : dbPersonStudies) {
				pbDBList.add(PersonUtils.getPersonBeanByPerson(ps.getPerson(), true));
			}
		}

		List<PersonBean> enqueteursBean = studyBean.getEnqueteurs();
		dbPersonStudiesMatchSelectedPS = (enqueteursBean.containsAll(pbDBList) && pbDBList.containsAll(enqueteursBean));
		if (!dbPersonStudiesMatchSelectedPS) {
			// Delete existing personStudies
			for (PersonStudy ps : dbPersonStudies) {
				studyService.deletePersonStudy(ps);
			}

			if (enqueteursBean != null) {
				for (PersonBean enqueteurBean : enqueteursBean) {
					Person enqueteur = personService.getPersonById(enqueteurBean.getIdPerson());
					PersonStudy personStudy = new PersonStudy(enqueteur, study);
					studyService.savePersonStudy(personStudy);
				}
			}
		}
	}

	private Study getStudyByStudyBean(StudyBean studyBean) {
		Study study = null;
		if (studyBean != null) {
			study = new Study();

			study.setIdStudy(studyBean.getId());
			study.setTitle(studyBean.getTitle());
			study.setCreateDate(studyBean.getCreatedDate());
			study.setModifiedDate(studyBean.getModifiedDate());
			study.setCloseDate(studyBean.getCloseDate());

			Person creator = personService.getPersonById(studyBean.getCreatorId());
			study.setCreatedBy(creator);

			Person modifier = personService.getPersonById(studyBean.getModifierId());
			study.setModifiedBy(modifier);

			Customer customer = customerService.getCustomerByName(studyBean.getCustomer());
			study.setCustomer(customer);

			PersonBean closerBean = studyBean.getCloser();
			if (closerBean != null) {
				Person closer = personService.getPersonById(closerBean.getIdPerson());
				study.setCloser(closer);
			}

			study.setStartDate(studyBean.getStartDate());
			study.setEndDate(studyBean.getEndDate());

			status = commonService.getStatusByStatusName(studyBean.getStatus());
			study.setStatus(status);

			// Supervisor
			Person supervisor = personService.getPersonById(studyBean.getSupervisorId());
			study.setSupervisor(supervisor);

			// Validator
			PersonBean validatorBean = studyBean.getValidator();
			if (validatorBean != null) {
				Person validator = personService.getPersonById(validatorBean.getIdPerson());
				study.setValidator(validator);
				study.setValidateDate(studyBean.getValidateDate());
			}

		}
		return study;
	}

	private List<StudyBean> getActiveStudies() {
		log.debug("Getting Active studies");
		List<StudyBean> result = null;
		if (status.getIdStatus() != Constant.ACTIVE_STATUS_ID) {
			status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);
		}
		// Set<Study> studies = studyService.getStudiesByStatus(status);
		Set<Study> studies = studyService.getStudies();
		if (studies != null && !studies.isEmpty()) {
			result = new ArrayList<StudyBean>();
			for (Study study : studies) {
				result.add(getStudyBeanFromStudy(study, true));
			}
		}
		return result;
	}

	/**
	 * 
	 * @param study
	 * @param lazyMode:
	 *            if false, then we load the object in eager mode
	 * @return
	 */
	private StudyBean getStudyBeanFromStudy(Study study, boolean lazyMode) {
		StudyBean studyBean = null;
		if (study != null) {
			studyBean = new StudyBean();
			studyBean.setId(study.getIdStudy());
			studyBean.setTitle(study.getTitle());
			Status status2 = study.getStatus();
			String statusString = (status2.getStatus().equals(Constant.ACTIVE_STATUS)) ? Constant.OPENED_STATUS_FEMALE
					: Constant.CLOSED_STATUS_FEMALE;
			studyBean.setStatus(statusString);
			studyBean.setCustomer(study.getCustomer().getName());
			studyBean.setStartDate(study.getStartDate());
			studyBean.setEndDate(study.getEndDate());
			studyBean.setCloseDate(study.getCloseDate());

			Person supervisor = study.getSupervisor();
			if (supervisor != null) {
				PersonBean supervisorBean = PersonUtils.getPersonBeanByPerson(supervisor, true);
				studyBean.setSupervisor(supervisorBean.getFullname());
				studyBean.setSupervisorId(supervisorBean.getIdPerson());
			}

			Person validator = study.getValidator();
			if (validator != null) {
				PersonBean validatorBean = PersonUtils.getPersonBeanByPerson(validator, true);
				studyBean.setValidator(validatorBean);
			}
			studyBean.setValidateDate(study.getValidateDate());

			Person closer = study.getCloser();
			if (closer != null) {
				PersonBean closerBean = PersonUtils.getPersonBeanByPerson(closer, true);
				studyBean.setCloser(closerBean);
			}

			if (!lazyMode) {
				// enqueteurs
				Set<PersonStudy> personStudies = studyService.getPersonStudyListByStudy(study);
				if (personStudies != null && !personStudies.isEmpty()) {
					for (PersonStudy personStudy : personStudies) {
						Person enqueteur = personStudy.getPerson();
						PersonBean enqueteurBean = PersonUtils.getPersonBeanByPerson(enqueteur, true);
						personService.setCoordinates(enqueteur, enqueteurBean);

						// We calculate punctuality and diligency average only for closed studies
						Status closedStatus = commonService.getStatusByStatusId(Constant.INACTIVE_STATUS_ID);
						Set<PersonStudy> studiesAttendedByThisEnqueteur = studyService
								.getPersonStudyListByPersonAndStudyStatus(enqueteur, closedStatus);
						enqueteurBean = PersonUtils.setMarks(studiesAttendedByThisEnqueteur, enqueteurBean);

						studyBean.addEnqueteur(enqueteurBean);
					}
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

	public void validateStudy(StudyBean studyBean) {
		studyBean.setValidateDate(new Date());
		studyBean.setValidator(PersonUtils.getPersonBeanByPerson(getConnectedUser(), true));
		this.studyBean = studyBean;
		register();
	}

	private Person getConnectedUser() {
		return SessionUtils.getConnectedPerson();
	}

	public String forwardToAddStudy(StudyBean studyBean) {
		studyBean = (studyBean != null) ? studyBean : new StudyBean();
		if (studyBean.getId() != 0) {
			// Eager loading
			study = studyService.getStudyById(studyBean.getId(), false);
			studyBean = getStudyBeanFromStudy(study, false);
		}
		this.studyBean = studyBean;
		setCustomersMap();
		setSupervisorsMap();
		setAvailableAgentsMaps();

		personStudiesBean = new HashSet<>();
		setPersonStudiesBeanSetFromDB();
		return Constant.VIEW_ADD_STUDY_PAGE_OUTCOME;
	}

	public String forwardToViewAgent(PersonBean agentBean) {
		if (agentBean == null)
			return null;
		return Constant.VIEW_USER_PAGE_OUTCOME;
	}

	private void setAvailableAgentsMaps() {
		List<PersonBean> allAgents = personService.getAgentsByStatus(status, true);
		if (allAgents != null) {
			if (availableAgentsStringMap == null) {
				availableAgentsStringMap = new HashMap<>();
			}
			for (PersonBean agent : allAgents) {
				availableAgentsStringMap.put(agent.getFullname(), agent.getIdPerson());
			}
		}
	}

	public String forwardToManageCustomers() {
		return Constant.VIEW_CUSTOMERS_PAGE_OUTCOME;
	}

	public void delete(StudyBean studyBean) {
		Study study = studyService.getStudyById(studyBean.getId());
		studyService.delete(study);
	}

	public void closeStudy() {
		studyBean.setStatus(Constant.INACTIVE_STATUS);
		studyBean.setCloseDate(new Date());
		studyBean.setCloser(PersonUtils.getPersonBeanByPerson(getConnectedUser(), true));

		// this.studyBean = studyBean;
		register();
		setPersonStudySetDBFromPSSetBean();
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Enqueteur noté", ((PersonBean) event.getObject()).getFullname());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((PersonBean) event.getObject()).getFullname());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public Map<String, Integer> getSupervisorsStringMap() {
		return supervisorsStringMap;
	}

	public void setSupervisorsStringMap(Map<String, Integer> supervisorsStringMap) {
		this.supervisorsStringMap = supervisorsStringMap;
	}

	public Map<String, Integer> getSelectedAgentsStringMap() {
		return selectedAgentsStringMap;
	}

	public void setSelectedAgentsStringMap(Map<String, Integer> selectedAgentsStringMap) {
		this.selectedAgentsStringMap = selectedAgentsStringMap;
	}

	public Map<String, Integer> getAvailableAgentsStringMap() {
		return availableAgentsStringMap;
	}

	public void setAvailableAgentsStringMap(Map<String, Integer> availableAgentsStringMap) {
		this.availableAgentsStringMap = availableAgentsStringMap;
	}

	public int getSelectedAgentIdToBeAddedToTeam() {
		return selectedAgentIdToBeAddedToTeam;
	}

	public void setSelectedAgentIdToBeAddedToTeam(int selectedAgentIdToBeAddedToTeam) {
		this.selectedAgentIdToBeAddedToTeam = selectedAgentIdToBeAddedToTeam;
	}

	public Set<PersonStudyBean> getPersonStudiesBean() {
		return personStudiesBean;
	}

	public void setPersonStudiesBean(Set<PersonStudyBean> personStudiesBean) {
		this.personStudiesBean = personStudiesBean;
	}

}
