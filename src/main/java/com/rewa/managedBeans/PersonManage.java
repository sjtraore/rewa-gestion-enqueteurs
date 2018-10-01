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
	
	private PersonBean agentBean = new PersonBean();
	
	@ManagedProperty("#{personService}")
	private PersonService personService;
	
	@ManagedProperty("#{commonService}")
	private CommonService commonService;
	
	@PostConstruct
    public void init() {
		rolesList = new ArrayList<String>();
		for(Role role: commonService.getALLRoles()) {
			rolesList.add(role.getRole());
		}
	}
	
	public String register() {
		Person person = PersonUtils.getPersonByPersonBean(agentBean);
		Status status = commonService.getActiveStatus();
		person.setStatus(status);
		//Role
		List<Role> roles = person.getRoles();
		if(roles == null) {
			person.setRoles(new ArrayList<Role>());
		}
		for(String rolename : selectedRoles) {
			Role role = commonService.getRoleByRoleName(rolename);
			if(role != null)
				person.addRole(role);
		}
		
		// Calling Business Service
		personService.register(person);
		
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("La personne " + this.agentBean.getFullname() + " a été enregistré avec succès"));
		log.debug("New person registred: " + person.getIdPerson());
		return Constant.ADMIN_MAIN_PAGE;
	}
	
	public String forwardToAddUser() {
		return Constant.ADD_USER_PAGE_OUTCOME;
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
		agents = personService.getAllAgents();
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

}
