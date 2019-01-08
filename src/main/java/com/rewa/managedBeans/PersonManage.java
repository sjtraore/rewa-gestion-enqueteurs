package com.rewa.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rewa.beans.PersonBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Coordinate;
import com.rewa.hibernate.data.CoordinateType;
import com.rewa.hibernate.data.Diploma;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Role;
import com.rewa.hibernate.data.Status;
import com.rewa.spring.service.CommonService;
import com.rewa.spring.service.PersonService;
import com.rewa.utils.RewaUtils;
import com.rewa.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class PersonManage {
	private static final Logger log = Logger.getLogger(PersonManage.class);

	private List<PersonBean> agents;
	private List<PersonBean> filteredAgents;
	private List<Person> persons;
	private List<Person> filteredPersons;
	private List<String> rolesList;
	private String[] selectedRoles = null;
	// Diploma
	private List<String> schoolLevelList;
	private String[] selectedDiplomas = null;
	private Status status;
	private Person connectedUser;

	private PersonBean agentBean;
	private Person person;

	@ManagedProperty("#{personService}")
	private PersonService personService;

	@ManagedProperty("#{commonService}")
	private CommonService commonService;

	private Integer ratingBase;
	
	private boolean displayAllUsers;
	
	private boolean connectedUserIsAdmin;

	@PostConstruct
	public void init() {
		ratingBase = Constant.RATING_BASE;
		HttpSession session = SessionUtils.getSession();
		setConnectedUser((Person) session.getAttribute("connectedUser"));

		List<Role> connectedUserRoles = (connectedUser != null) ? connectedUser.getRoles() : new ArrayList<Role>();

		Role adminRole = commonService.getRoleById(Constant.ADMIN_ROLE_ID);

		person = getPersonByPersonBean(person, agentBean);
		rolesList = new ArrayList<String>();
		setConnectedUserIsAdmin(connectedUserRoles.contains(adminRole));
		// Only an admin can see and give Admin role to someone else
		for (Role role : commonService.getALLRoles()) {
			if (role.getIdRole() != Constant.ADMIN_ROLE_ID || isConnectedUserIsAdmin())
				rolesList.add(role.getRole());
		}
		status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);

		schoolLevelList = new ArrayList<String>();
		for (Diploma diploma : commonService.getALLDiplomas()) {
			schoolLevelList.add(diploma.getDiploma());
		}

	}

	public String register() {
		person = getPersonByPersonBean(person, agentBean);

		/**************** Coordinates *********************/
		setCoordinates();
		
		// Roles
		// If no role selected, check if the person already has any role and remove it
		if (selectedRoles == null || selectedRoles.length == 0) {
			person.setRoles(null);
		} else {
			for (String rolename : selectedRoles) {
				Role role = commonService.getRoleByRoleName(rolename);
				// be sure the person doesn't already have the role
				if (role != null && (person.getRoles() == null || !person.getRoles().contains(role))) {
					person.addRole(role);
				}
			}
		}

		/**************** Diplomas *********************/
		// If no Diploma selected, check if the person already has any diploma and
		// remove it
		if (selectedDiplomas == null || selectedDiplomas.length == 0) {
			person.setDiplomas(null);
		} else {
			//If only selected diplomas in UI is different than the registered 
			//ones in database then update the database
			Set<Diploma> personDiplomas = person.getDiplomas();
			boolean dbDiplomasMatchesSelectedDiplomas = personDiplomas.stream().map(Diploma::getDiploma)
					.collect(Collectors.toSet()).equals(selectedDiplomas);
			
			log.debug("dbDiplomasMatchesSelectedDiplomas: " + dbDiplomasMatchesSelectedDiplomas);
			if(!dbDiplomasMatchesSelectedDiplomas) {
				HashSet<Diploma> diplomas = new HashSet<Diploma>();
				person.getDiplomas().clear();
				//person.setDiplomas(diplomas);
				for (String diplomaname : selectedDiplomas) {
					Diploma diploma = commonService.getDiplomaByDiplomaName(diplomaname);
					if (diploma != null 
							&& (personDiplomas == null || !personDiplomas.contains(diploma))) {
						diplomas.add(diploma);
					}
				}
				person.setDiplomas(diplomas);
			}
		}

		/**************** Rating *********************/
		// Languages

		// Calling Business Service
		personService.save(person);

		// Add message
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("La personne " + this.agentBean.getFullname() + " a été enregistré avec succés"));
		log.debug("New person registred: " + person.getIdPerson());
		return Constant.USER_HOME_PAGE_OUTCOME;
	}

	private void setCoordinates() {
		Status coordinateStatus = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);

		// Primary phone
		String address = agentBean.getAddress();
		addCoordinate(coordinateStatus, address, Constant.COORDINATE_TYPE_ADDRESS);

		// Primary phone
		String primaryPhone = agentBean.getPrimaryPhone();
		addCoordinate(coordinateStatus, primaryPhone, Constant.COORDINATE_TYPE_PRIMARY_PHONE);

		// Secondary phone
		String secondaryPhone = agentBean.getSecondaryPhone();
		addCoordinate(coordinateStatus, secondaryPhone, Constant.COORDINATE_TYPE_SECONDARY_PHONE);

		// email address
		String primaryEmail = agentBean.getPrimaryEmail();
		addCoordinate(coordinateStatus, primaryEmail, Constant.COORDINATE_TYPE_PRIMARY_EMAIL);

		// facebook id
		String facebookId = agentBean.getFacebook();
		addCoordinate(coordinateStatus, facebookId, Constant.COORDINATE_TYPE_FACEBOOK_ID);

	}

	/**
	 * Add a coordinate value to the current personBean
	 * 
	 * @param coordinateStatus
	 * @param coordinateValue
	 * @param coordinateTypeId
	 */
	private void addCoordinate(Status coordinateStatus, String coordinateValue, int coordinateTypeId) {
		if (coordinateValue == null)
			return;

		Coordinate coordinate = null;
		CoordinateType ct = commonService.getCoordinateTypeById(coordinateTypeId);
		try {
			List<Coordinate> coordinates = personService.getCoordinateByOwnerTypeAndStatus(person, ct, status);
			if (coordinates != null && !coordinates.isEmpty()) {
				coordinate = coordinates.get(0);
			}
		} catch (Exception e) {
			log.debug(e, e);
		}
		if (coordinate == null) {
			coordinate = new Coordinate();
			coordinate.setCreatedDate(new Date());
			coordinate.setCreatedBy(connectedUser);
		}

		String coordinateFromDatabase = coordinate.getCoordinate();
		if (coordinateFromDatabase == null && coordinateValue.trim().isEmpty()) {
			return;
		} else if (!coordinateValue.equals(coordinateFromDatabase)) {
			coordinate.setType(ct);
			coordinate.setCoordinate(coordinateValue);
			coordinate.setStatus(coordinateStatus);
			coordinate.setModifiedDate(new Date());
			coordinate.setModifiedBy(connectedUser);
			coordinate.setPriority(1);
			if (person != null) {
				coordinate.setOwner(person);
				personService.saveCoordinate(coordinate);
			}
		}
	}

	public String forwardToAddUser(PersonBean agentBean) {
		System.out.println("Agent: " + agentBean);
		this.agentBean = agentBean;
		return Constant.ADD_USER_PAGE_OUTCOME;
	}

	public String forwardToViewUser(PersonBean agentBean) {
		System.out.println("Agent: " + agentBean);
		this.agentBean = agentBean;
		return Constant.VIEW_USER_PAGE_OUTCOME;
	}

	public String softDeleteAgent(PersonBean agentBean) {
		System.out.println("Soft deleting: " + agentBean);
		Person person = getPersonByPersonBean(null, agentBean);
		Status inactiveStatus = commonService.getStatusByStatusName(Constant.INACTIVE_STATUS);
		person.setStatus(inactiveStatus);

		personService.save(person);

		RewaUtils.addMessage(FacesMessage.SEVERITY_INFO, "Agent désactivé", null);
		return "";
	}

	//Hard delete. Allowed only for admin
	public String deletePerson(PersonBean agentBean) {
		if(isConnectedUserIsAdmin()) {
			log.debug("Hard deleting PersonBean: " + agentBean);
			Person person = getPersonByPersonBean(null, agentBean);
			//Allow only deleting inactive person
			Status inactiveStatus = commonService.getStatusByStatusName(Constant.INACTIVE_STATUS);
			if(person.getStatus().equals(inactiveStatus)) {
				personService.delete(person);

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Utilisateur, " + person + ", définitement supprimé."));
				
			} else {
				// Add message
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("On ne peut pas supprimer un utilisateur actif. "
								+ "\nMerci de désactivé avant en utilisant le bouton Supprimer' à côté de l\'icône poubelle."));
				log.debug("Person person NOT deleted");
				
			}
		}
		return Constant.USER_HOME_PAGE_OUTCOME;
	}

	/**
	 * Returns PersonBean object from Person. 
	 * If person in parameter is null then returns new Person to be probably registered in database
	 * @param person
	 * @param personBean
	 * @return
	 */
	private Person getPersonByPersonBean(Person person, PersonBean personBean) {
		//TODO check if person in parameter is necessary
		if (personBean != null) {
			person = new Person();
			int idPerson = personBean.getIdPerson();
			if (idPerson != 0) {
				person = personService.getPersonById(idPerson);
			}
			person.setFirstname(personBean.getFirstname());
			person.setLastname(personBean.getLastname());

			person.setCreatedDate(personBean.getCreatedDate());
			person.setModifiedDate(personBean.getModifiedDate());
			person.setUsername(personBean.getUsername());

			String passwordBean = personBean.getPassword();
			if (passwordBean != null && !passwordBean.trim().isEmpty() && !passwordBean.equals(person.getPassword()))
				person.setPassword(passwordBean);

			/************ Rôles ***********/
			List<String> rolenames = personBean.getRoles();
			if (rolenames != null && !rolenames.isEmpty()) {
				try {
					List<Role> roles = new ArrayList<Role>();
					for (String rolename : rolenames) {
						Role role = commonService.getRoleByRoleName(rolename);
						roles.add(role);
					}
					person.setRoles(roles);
				} catch (Exception e) {
					log.error(e, e);
				}
			}

			/*********** Status ************/
			status = commonService.getStatusByStatusName(personBean.getStatus());
			person.setStatus(status);

		}
		return person;
	}

	public String cancelUerRegistration() {
		return Constant.USER_HOME_PAGE_OUTCOME;
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
		System.out.println("Agents : " + agents);
		return agents;
	}

	public List<PersonBean> getActiveAgents() {
		agents = personService.getAgentsByStatus(status);
		return agents;
	}
	
	public List<PersonBean> getActiveSystemUsers() {
		return personService.getActiveSystemUsers(displayAllUsers);
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
		if (agentBean == null) {
			agentBean = new PersonBean();
		}
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
		selectedRoles = null;
		if (person == null || person.getIdPerson() != agentBean.getIdPerson()) {
			person = getPersonByPersonBean(person, agentBean);
		}
		if (person != null && person.getRoles() != null && !person.getRoles().isEmpty()) {
			int personRoleList = person.getRoles().size();
			selectedRoles = new String[personRoleList];
			for (int i = 0; i < personRoleList; i++) {
				selectedRoles[i] = (person.getRoles().get(i)).getRole();
			}
		}
		System.out.println("person: " + person);
		System.out.println("selectedRoles: " + selectedRoles);
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

	public Person getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(Person connectedUser) {
		this.connectedUser = connectedUser;
	}

	public List<String> getSchoolLevelList() {
		return schoolLevelList;
	}

	public void setSchoolLevelList(List<String> schoolLevelList) {
		this.schoolLevelList = schoolLevelList;
	}

	public String[] getSelectedDiplomas() {
		if (person == null || person.getIdPerson() != agentBean.getIdPerson()) {
			person = getPersonByPersonBean(person, agentBean);
		}

		Set<Diploma> diplomas = person.getDiplomas();
		int nb = (diplomas != null) ? diplomas.size() : 0;
		if (nb > 0) {
			selectedDiplomas = new String[nb];
			int counter = 0;
			for (Diploma diploma : diplomas) {
				selectedDiplomas[counter] = diploma.getDiploma();
				counter++;
			}
		}
		System.out.println("selectedDiplomas: " + selectedDiplomas);
		return selectedDiplomas;
	}

	public void setSelectedDiplomas(String[] selectedDiplomas) {
		this.selectedDiplomas = selectedDiplomas;
	}

	public Integer getRatingBase() {
		return ratingBase;
	}

	public void setRatingBase(Integer ratingBase) {
		this.ratingBase = ratingBase;
	}

	public boolean isDisplayAllUsers() {
		return displayAllUsers;
	}

	public void setDisplayAllUsers(boolean displayAllUsers) {
		this.displayAllUsers = displayAllUsers;
	}

	public boolean isConnectedUserIsAdmin() {
		return connectedUserIsAdmin;
	}

	public void setConnectedUserIsAdmin(boolean connectedUserIsAdmin) {
		this.connectedUserIsAdmin = connectedUserIsAdmin;
	}

}
