package com.rewa.utils;

import com.rewa.beans.PersonBean;
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
			personBean.setPassword(person.getPassword());
			
			Person personCreator = person.getCreator();
			if(personCreator != null) {
				personBean.setCreatorName(personCreator.getFirstname() + " " + personCreator.getLastname());
			}
			Person personUpdator = person.getUpdator();
			if(personUpdator != null) {
				personBean.setUpdatorName(personUpdator.getFirstname() + " " + personUpdator.getLastname());
			}
			
			
		}
		
		return personBean;
	}

}
