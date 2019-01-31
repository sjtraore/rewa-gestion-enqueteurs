package com.rewa.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

import com.rewa.beans.PersonBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Coordinate;
import com.rewa.hibernate.data.CoordinateType;
import com.rewa.hibernate.data.Diploma;
import com.rewa.hibernate.data.Field;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.PersonLevel;
import com.rewa.hibernate.data.PersonStudy;
import com.rewa.hibernate.data.Role;

public class PersonUtils {
	public static PersonBean getPersonBeanByPerson(Person person, boolean lazyMode) {
		PersonBean personBean = null;
		if (person != null) {
			personBean = new PersonBean();
			personBean.setIdPerson(person.getIdPerson());
			personBean.setFirstname(person.getFirstname());
			personBean.setLastname(person.getLastname());
			personBean.setFullname(person.getFirstname() + " " + person.getLastname());
			personBean.setStatus(person.getStatus().getStatus());
			personBean.setCreatedDate(person.getCreatedDate());
			personBean.setModifiedDate(person.getModifiedDate());
			personBean.setUsername(person.getUsername());
			// We don't want to fill the password for the user.
			// He will then see blank in password field
			personBean.setPassword(person.getPassword());

			Person personCreator = person.getCreator();
			if (personCreator != null) {
				personBean.setCreatorName(personCreator.getFirstname() + " " + personCreator.getLastname());
			}
			Person personUpdator = person.getUpdator();
			if (personUpdator != null) {
				personBean.setUpdatorName(personUpdator.getFirstname() + " " + personUpdator.getLastname());
			}

			if (!lazyMode) {
				// List of roles
				Set<Role> roles = person.getRoles();
				if (roles != null) {
					int nbRoles = roles.size();
					String[] selectedRoles = new String[nbRoles];
					int counter = 0;
					for (Role role : roles) {
						selectedRoles[counter] = role.getRole();
						counter++;
					}
					personBean.setSelectedRoles(selectedRoles);
				}

				// List of diplomas
				Set<Diploma> diplomas = person.getDiplomas();
				if (diplomas != null) {
					int nbDiplomas = diplomas.size();
					String[] selectedDiplomas = new String[nbDiplomas];
					int counter = 0;
					for (Diploma diploma : diplomas) {
						selectedDiplomas[counter] = diploma.getDiploma();
						counter++;
					}
					personBean.setSelectedDiplomas(selectedDiplomas);
				}

				// Languages
				Set<PersonLevel> levels = person.getPersonLevels();
				if (levels != null) {
					for (PersonLevel pl : levels) {
						Field field = pl.getField();
						switch (field.getIdField()) {
						case Constant.ID_RATING_FRENCH_ORAL:
							personBean.setRatingFrenchOral(pl.getNotation());
							break;
						case Constant.ID_RATING_FRENCH_WRITING:
							personBean.setRatingFrenchWriting(pl.getNotation());
							break;
						case Constant.ID_RATING_LOCAL_LANGUAGES:
							personBean.setRatingLocalLanguage(pl.getNotation());
							personBean.setLocalLanguages(pl.getObservation());
							break;
						case Constant.ID_RATING_MS_WORD:
							personBean.setRatingMSWord(pl.getNotation());
							break;
						case Constant.ID_RATING_MS_EXCEL:
							personBean.setRatingMSExcel(pl.getNotation());
							break;
						case Constant.ID_RATING_MS_POWERPOINT:
							personBean.setRatingMSPowerpoint(pl.getNotation());
							break;
						default:
							break;
						}
					}
				}

				// Coordinates
				Set<Coordinate> coordinates = person.getCoordinates();
				if (coordinates != null && !coordinates.isEmpty()) {
					for (Coordinate coordinate : coordinates) {
						// Only active coordinates are handled
						if (coordinate.getStatus().getIdStatus() == Constant.ACTIVE_STATUS_ID) {
							CoordinateType coordinateType = coordinate.getType();
							
							if(coordinateType == null)
								continue;
							
							switch (coordinateType.getIdCoordinateType()) {
							case Constant.COORDINATE_TYPE_PRIMARY_PHONE:
								personBean.setPrimaryPhone(coordinate.getCoordinate());
								break;
							case Constant.COORDINATE_TYPE_SECONDARY_PHONE:
								personBean.setSecondaryPhone(coordinate.getCoordinate());
								break;
							case Constant.COORDINATE_TYPE_PRIMARY_EMAIL:
								personBean.setPrimaryEmail(coordinate.getCoordinate());
								break;
							case Constant.COORDINATE_TYPE_FACEBOOK_ID:
								personBean.setFacebook(coordinate.getCoordinate());
								break;
							default:
								break;
							}
						}
					}
				}
				
				//Marks Punctuality and diligency
				//personBean = setMarks(person, personBean);
			}
		}
		return personBean;
	}

	/**
	 * Set marks (punctuality, diligence...)
	 * @param person
	 * @param personBean
	 * This is calculate for closed studies only 
	 * because open studies don't necessary have marks
	 */
	public static PersonBean setMarks(Set<PersonStudy> studies, PersonBean personBean) {
		if(studies != null && !studies.isEmpty()) {
			//Calculating average mark of punctuality
			BigDecimal avgPunctuality = BigDecimal.ZERO;
			BigDecimal avgDiligence = BigDecimal.ZERO;
			Integer sumPunctuality = 0;
			Integer sumDiligence = 0;
			for(PersonStudy personStudy : studies) {
				Integer markPunctuality = personStudy.getMarkPunctuality();
				if(markPunctuality != null)
					sumPunctuality += markPunctuality;
				Integer markDiligence = personStudy.getMarkDiligence();
				if(markDiligence != null)
					sumDiligence += markDiligence;
			}
			avgPunctuality = new BigDecimal(sumPunctuality).divide(new BigDecimal(studies.size()), 2, RoundingMode.HALF_UP);
			avgDiligence = new BigDecimal(sumDiligence).divide(new BigDecimal(studies.size()), 2, RoundingMode.HALF_UP);
			personBean.setAvgPunctuality(avgPunctuality);
			personBean.setAvgDiligence(avgDiligence);
		}
		return personBean;
	}

	/**
	 * Returns PersonBean loading its collections (roles, diplomas, languages,
	 * technical skills)
	 * 
	 * @param personBean
	 * @param person
	 * @return
	 */
	public static PersonBean getPersonBeanByPersonEager(Person person) {
		return getPersonBeanByPerson(person, false);
	}

	public static Set<PersonBean> getPersonBeanListFromPersonList(Set<Person> persons, boolean lazyMode) {
		Set<PersonBean> result = null;
		if (persons != null && !persons.isEmpty()) {
			result = new HashSet<PersonBean>();
			for (Person person : persons) {
				result.add(getPersonBeanByPerson(person, lazyMode));
			}
		}
		return result;
	}

	public static PersonBean addCoordinatesToPersonBean(PersonBean personBean, Coordinate coordinate) {
		if (coordinate != null) {
			CoordinateType ct = coordinate.getType();
			switch (ct.getIdCoordinateType()) {
			case 1:
				// Primary phone
				personBean.setPrimaryPhone(coordinate.getCoordinate());
				break;
			case 2:
				// Secondary phone
				personBean.setSecondaryPhone(coordinate.getCoordinate());
				break;
			default:
				break;
			}
		}
		return personBean;
	}

}
