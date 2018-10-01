package com.rewa.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.rewa.beans.PersonBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Role;
import com.rewa.hibernate.data.Status;
import com.rewa.spring.service.CommonService;
import com.rewa.spring.service.PersonService;
import com.rewa.utils.PersonUtils;
import com.rewa.utils.RewaUtils;

@ManagedBean
@SessionScoped
public class PersonManage {
	private static final Logger log = Logger.getLogger(PersonManage.class);

	private List<PersonBean> agents;
	private List<PersonBean> filteredAgents;
	private List<Person> persons;
	private List<Person> filteredPersons;
	private List<String> rolesList;
	private String[] selectedRoles;
	private Status status;

	private PersonBean agentBean = new PersonBean();
	private Person person;

	@ManagedProperty("#{personService}")
	private PersonService personService;

	@ManagedProperty("#{commonService}")
	private CommonService commonService;

	@PostConstruct
	public void init() {
		person = PersonUtils.getPersonByPersonBean(agentBean);
		rolesList = new ArrayList<String>();
		for (Role role : commonService.getALLRoles()) {
			rolesList.add(role.getRole());
		}
		status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);
		if (person != null && person.getRoles() != null && !person.getRoles().isEmpty()) {
			int personRoleList = person.getRoles().size();
			selectedRoles = new String[personRoleList];
			for (int i = 0; i < personRoleList; i++) {
				selectedRoles[i] = (person.getRoles().get(i)).getRole();
			}
		}
	}

	public String register() {
		status = commonService.getStatusByStatusName(agentBean.getStatus());
		person.setStatus(status);
		// Role
		List<Role> roles = person.getRoles();
		if (roles == null) {
			person.setRoles(new ArrayList<Role>());
		}
		for (String rolename : selectedRoles) {
			Role role = commonService.getRoleByRoleName(rolename);
			if (role != null)
				person.addRole(role);
		}

		// Calling Business Service
		personService.save(person);

		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("La personne " + this.agentBean.getFullname() + " a été enregistré avec succès"));
		log.debug("New person registred: " + person.getIdPerson());
		return Constant.ADMIN_MAIN_PAGE;
	}

	public String forwardToAddUser(PersonBean agentBean) {
		System.out.println("Agent: " + agentBean);
		this.agentBean = agentBean;
		return Constant.ADD_USER_PAGE_OUTCOME;
	}

	public String softDeleteAgent(PersonBean agentBean) {
		System.out.println("Soft deleting: " + agentBean);
		Person person = PersonUtils.getPersonByPersonBean(agentBean);
		Status inactiveStatus = commonService.getStatusByStatusName(Constant.INACTIVE_STATUS);
		person.setStatus(inactiveStatus);

		personService.save(person);

		RewaUtils.addMessage(FacesMessage.SEVERITY_INFO, "Agent désactivé", null);
		return "";
	}

	public String deletePerson(PersonBean agentBean) {
		System.out.println("Deleting: " + agentBean);
		RewaUtils.addMessage(FacesMessage.SEVERITY_INFO, "Utilisateur suprimé", null);
		return "";
	}

	public String cancelUerRegistration() {
		return Constant.ADMIN_PAGE_OUTCOME;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Person> getFilteredPersons() {
		return filteredPersons;
	}

	public void setFilteredPersons(List<Person> filteredPersons) {
		this.filteredPersons = filteredPersons;
	}

	public List<PersonBean> getAgents() {
		agents = personService.getAgentsByStatus(status);
		return agents;
	}

	public List<PersonBean> getActiveAgents() {
		agents = personService.getAgentsByStatus(status);
		return agents;
	}

	public void setAgents(List<PersonBean> personsBean) {
		this.agents = personsBean;
	}

	public List<PersonBean> getFilteredAgents() {
		return filteredAgents;
	}

	public void setFilteredAgents(List<PersonBean> filteredAgents) {
		this.filteredAgents = filteredAgents;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public PersonBean getAgentBean() {
		return agentBean;
	}

	public void setAgentBean(PersonBean agentBean) {
		this.agentBean = agentBean;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public List<String> getRoles() {
		return rolesList;
	}

	public void setRoles(List<String> roles) {
		this.rolesList = roles;
	}

	public List<String> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<String> rolesList) {
		this.rolesList = rolesList;
	}

	public String[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
