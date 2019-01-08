package com.rewa.utils;

import java.util.Set;

import com.rewa.beans.PersonBean;
import com.rewa.hibernate.data.Coordinate;
import com.rewa.hibernate.data.CoordinateType;
import com.rewa.hibernate.data.Diploma;
import com.rewa.hibernate.data.Person;

public class PersonUtils {
	public static PersonBean getPersonBeanByPerson(Person person){
		PersonBean personBean = null;
		if(person != null) {
			personBean = new PersonBean();
			personBean.setIdPerson(person.getIdPerson());
			personBean.setFirstname(person.getFirstname());
			personBean.setLastname(person.getLastname());
			personBean.setFullname(person.getFirstname() + " " + person.getLastname());
			personBean.setStatus(person.getStatus().getStatus());
			personBean.setCreatedDate(person.getCreatedDate());
			personBean.setModifiedDate(person.getModifiedDate());
			personBean.setUsername(person.getUsername());
			//We don't want to fill the password for the user. 
			//He will then see blank in password field
			personBean.setPassword(person.getPassword());
			
			Person personCreator = person.getCreator();
			if(personCreator != null) {
				personBean.setCreatorName(personCreator.getFirstname() + " " + personCreator.getLastname());
			}
			Person personUpdator = person.getUpdator();
			if(personUpdator != null) {
				personBean.setUpdatorName(personUpdator.getFirstname() + " " + personUpdator.getLastname());
			}
			
			//List of diplomas
			Set<Diploma> diplomas = person.getDiplomas();
			if(diplomas != null) {
				int nbDiplomas = diplomas.size();
				String[] selectedDiplomas = new String[nbDiplomas];
				int counter = 0;
				for(Diploma diploma : diplomas) {
					selectedDiplomas[counter] = diploma.getDiploma();
					counter++;
				}
				personBean.setSelectedDiplomas(selectedDiplomas);
			}
		}
		return personBean;
	}
	
	public static PersonBean addCoordinatesToPersonBean(PersonBean personBean, Coordinate coordinate){
		if(coordinate != null) {
			CoordinateType ct = coordinate.getType();
			switch(ct.getIdCoordinateType()) {
			case 1:
				//Primary phone
				personBean.setPrimaryPhone(coordinate.getCoordinate());
				break;
			case 2: 
				//Secondary phone
				personBean.setSecondaryPhone(coordinate.getCoordinate());
				break;
			default:
				break;
			}
		}
		return personBean;
	}

}
