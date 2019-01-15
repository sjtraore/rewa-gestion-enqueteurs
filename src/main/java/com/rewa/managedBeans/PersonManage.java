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
import com.rewa.hibernate.data.Field;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.PersonLevel;
import com.rewa.hibernate.data.PersonLevelPK;
import com.rewa.hibernate.data.Role;
import com.rewa.hibernate.data.Status;
import com.rewa.spring.service.CommonService;
import com.rewa.spring.service.PersonService;
import com.rewa.utils.PersonUtils;
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
	// Diploma
	private List<String> schoolLevelList;
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

		Set<Role> connectedUserRoles = (connectedUser != null) ? connectedUser.getRoles() : new HashSet<Role>();

		Role adminRole = commonService.getRoleById(Constant.ADMIN_ROLE_ID);

		person = getPersonByPersonBean(person, agentBean);

		status = commonService.getStatusByStatusName(Constant.ACTIVE_STATUS);
		agents = personService.getAgentsByStatus(status, true);
		
		rolesList = new ArrayList<String>();
		setConnectedUserIsAdmin(connectedUserRoles.contains(adminRole));
		// Only an admin can see and give Admin role to someone else
		for (Role role : commonService.getALLRoles()) {
			if (role.getIdRole() != Constant.ADMIN_ROLE_ID || isConnectedUserIsAdmin())
				rolesList.add(role.getRole());
		}

		schoolLevelList = new ArrayList<String>();
		for (Diploma diploma : commonService.getALLDiplomas()) {
			schoolLevelList.add(diploma.getDiploma());
		}

	}

	public String register() {
		person = getPersonByPersonBean(person, agentBean);

		/**************** Coordinates *********************/
		setCoordinates();

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
		if(agentBean != null) {
			Person eagerPerson = personService.getPersonByIdLoadingPersonLevels(agentBean.getIdPerson());
			agentBean = PersonUtils.getPersonBeanByPerson(eagerPerson, false);
		}
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
				//person = personService.getPersonById(idPerson);
				person = personService.getPersonByIdLoadingPersonLevels(idPerson);
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
			setRoles(person, personBean);

			/*********** Status ************/
			status = commonService.getStatusByStatusName(personBean.getStatus());
			person.setStatus(status);
			

			/**************** Diplomas *********************/
			setPersonDiplomas(person, personBean);
			
			/**************************** Languages **************************/
			setPersonLanguages(person, personBean);

		}
		return person;
	}

	private void setRoles(Person person, PersonBean personBean) {
		String[] selectedRoles = personBean.getSelectedRoles();
		
		// Roles
		// If no role selected, check if the person already has any role and remove it
		if (selectedRoles == null || selectedRoles.length == 0) {
			person.setRoles(null);
		} else {
			//If only selected diplomas in UI is different than the registered 
			//ones in database then update the database
			Set<Role> personRoles = person.getRoles();
			boolean dbRolesMatchSelectedRoles = personRoles.stream().map(Role::getRole)
					.collect(Collectors.toSet()).equals(selectedRoles);
			log.debug("dbRolesMatchSelectedRoles: " + dbRolesMatchSelectedRoles);
			
			if(!dbRolesMatchSelectedRoles) {
				Set<Role> roles = new HashSet<Role>();
				person.getRoles().clear();
				//person.setDiplomas(diplomas);
				for (String rolename : selectedRoles) {
					Role role = commonService.getRoleByRoleName(rolename);
					if (role != null 
							&& (personRoles == null || !personRoles.contains(role))) {
						roles.add(role);
					}
				}
				person.setRoles(roles);
			}
		}
	}
	
	/**
	 * Setting languages levels
	 * @param person
	 * @param personBean
	 */
	private void setPersonLanguages(Person person, PersonBean personBean) {
		//PersonLevel pl = null;
		Set<PersonLevel> plSetDB = person.getPersonLevels();
		
		Set<PersonLevel> plSet = new HashSet<PersonLevel>();
		
		//French Oral
		Integer frenchOral = personBean.getRatingFrenchOral();
		Field frenchOralField = commonService.getFieldById(Constant.ID_RATING_FRENCH_ORAL);
		buildPersonLevel(person, plSet, frenchOral, frenchOralField, null);
		
		//French Writing
		Integer frenchWriting = personBean.getRatingFrenchWriting();
		Field frenchWritingField = commonService.getFieldById(Constant.ID_RATING_FRENCH_WRITING);
		buildPersonLevel(person, plSet, frenchWriting, frenchWritingField, null);
		
		//Local Languages
		Integer localLanguage = personBean.getRatingLocalLanguage();
		Field localLanguageField = commonService.getFieldById(Constant.ID_RATING_LOCAL_LANGUAGES);
		String localLanguages = (agentBean != null) ? agentBean.getLocalLanguages() : null;
		buildPersonLevel(person, plSet, localLanguage, localLanguageField, localLanguages);
		
		if(!plSet.equals(plSetDB)) {
			person.getPersonLevels().clear();
			person.setPersonLevels(plSet);
		}
	}

	private void buildPersonLevel(Person person, Set<PersonLevel> plSet, Integer localLanguage,
			Field field, String observation) {
		if(field != null) {
			int languageValue = (localLanguage == null) ? 0 : localLanguage.intValue();
			PersonLevel pl = new PersonLevel();
			pl.setField(field);
			pl.setBaseNotation(Constant.RATING_BASE);
			pl.setEvaluator(connectedUser);
			pl.setNotation(languageValue);
			pl.setPerson(person);
			pl.setObservation(observation);
			
			PersonLevelPK plPK = new PersonLevelPK();
			plPK.setIdPerson(person.getIdPerson());
			plPK.setIdField(field.getIdField());
			plPK.setDateLevel(new Date());
			pl.setId(plPK);
			
			plSet.add(pl);
		}
	}

	/**
	 * Gets diploma names from personBean and add the matching diploma object to person
	 * If no Diploma selected, check if the person already has any diploma in database and remove it
	 * @param person
	 * @param personBean
	 */
	private void setPersonDiplomas(Person person, PersonBean personBean) {
		String[] selectedDiplomas = personBean.getSelectedDiplomas();
		if (selectedDiplomas == null || selectedDiplomas.length == 0) {
			person.setDiplomas(null);
		} else {
			//If only selected diplomas in UI is different than the registered 
			//ones in database then update the database
			Set<Diploma> personDiplomas = person.getDiplomas();
			boolean dbDiplomasMatchesSelectedDiplomas = personDiplomas.stream().map(Diploma::getDiploma)
					.collect(Collectors.toSet()).equals(selectedDiplomas);
			
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
		agents = personService.getAllAgents(true);
		System.out.println("Agents : " + agents);
		return agents;
	}

	public List<PersonBean> getActiveAgents() {
		return agents;
	}
	
	public List<PersonBean> getActiveSystemUsers() {
		return personService.getActiveSystemUsers(displayAllUsers, true);
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
