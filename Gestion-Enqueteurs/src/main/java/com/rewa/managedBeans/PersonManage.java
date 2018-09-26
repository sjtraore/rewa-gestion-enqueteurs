package com.rewa.managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.rewa.beans.PersonBean;
import com.rewa.hibernate.data.Person;
import com.rewa.spring.service.PersonService;

@ManagedBean
@SessionScoped
public class PersonManage {
	private List<PersonBean> agents;
	private List<PersonBean> filteredAgents;
	private List<Person> persons;
	private List<Person> filteredPersons;
	
	private Person agent;
	
	@ManagedProperty("#{personService}")
	private PersonService personService;
	
	public String register() {
		// Calling Business Service
		personService.register(agent);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("La personne " + this.agent.getFirstname() + " a été enregistrée avec succès"));
		return "";
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

	public Person getAgent() {
		return agent;
	}

	public void setAgent(Person agent) {
		this.agent = agent;
	}

}
