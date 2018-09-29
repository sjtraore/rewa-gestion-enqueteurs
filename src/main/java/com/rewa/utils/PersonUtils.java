package com.rewa.utils;

import com.rewa.beans.PersonBean;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Status;

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

	public static Person getPersonByPersonBean(PersonBean personBean, Status status) {
		Person person = null;
		if(personBean != null) {
			person = new Person();
			person.setIdPerson(personBean.getIdPerson());
			person.setFirstname(personBean.getFirstname());
			person.setLastname(personBean.getLastname());

			person.setStatus(status);
			
			person.setCreatedDate(personBean.getCreatedDate());
			person.setModifiedDate(personBean.getModifiedDate());
			person.setPassword(personBean.getPassword());
			
//			if(personBean.getCreatorId() != 0) {
//				Person creator = PersonService.
//			}
			
			
		}
		return person;
	}
}
