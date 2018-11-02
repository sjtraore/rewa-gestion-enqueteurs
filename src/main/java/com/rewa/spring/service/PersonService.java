package com.rewa.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.beans.PersonBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Coordinate;
import com.rewa.hibernate.data.CoordinateType;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.PersonDiploma;
import com.rewa.hibernate.data.Status;
import com.rewa.utils.PersonUtils;;

@Component
@Transactional
public class PersonService {
	@Autowired
	private SessionFactory sessionFactory;

	private CommonService commonService;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void save(Person person) {
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		person.setModifiedDate(new Date());
		// Save person, saving behavior get done in a transactional manner
		if (person.getIdPerson() == 0) {
			person.setCreatedDate(new Date());
			session.save(person);
		} else {
			session.merge(person);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Person> getPersons() {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			List<Person> result = session.createCriteria(Person.class).list();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Coordinate saveCoordinate(Coordinate coordinate) {
		if (coordinate != null) {
			Session session = sessionFactory.getCurrentSession();
			coordinate.setModifiedDate(new Date());
			if (coordinate.getIdcoordinate() == 0) {
				coordinate.setCreatedDate(new Date());
				session.save(coordinate);
			} else {
				session.merge(coordinate);
			}
		}
		return coordinate;
	}
	
	public PersonDiploma savePersonDiploma(PersonDiploma personDiploma) {
		if (personDiploma != null) {
			Session session = sessionFactory.getCurrentSession();
			if (personDiploma.getId() == null) {
				session.save(personDiploma);
			} else {
				session.merge(personDiploma);
			}
		}
		return personDiploma;
	}

	public List<PersonBean> getAllAgents() {
		List<PersonBean> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Person> persons = session.createCriteria(Person.class).list();
			// System.out.println("getAllAgents: " + ((persons != null) ? persons.size() :
			// 0));
			if (persons != null) {
				result = new ArrayList<PersonBean>();
				for (Person person : persons) {
					result.add(PersonUtils.getPersonBeanByPerson(person));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<PersonBean> getAgentsByStatus(Status status) {
		List<PersonBean> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Person.class).add(Restrictions.eq("status", status));
			@SuppressWarnings("unchecked")
			List<Person> persons = criteria.list();
			if (persons != null) {
				result = new ArrayList<PersonBean>();

				for (Person person : persons) {
					PersonBean personBean = PersonUtils.getPersonBeanByPerson(person);
					/********** Coordinates *************/
					Status activeCoordinateStatus = commonService
							.getStatusByStatusId(Constant.ACTIVE_STATUS_ID);
					//Address
					CoordinateType ct = commonService.getCoordinateTypeById(Constant.COORDINATE_TYPE_ADDRESS);
					setPersonBeanCoordinate(person, personBean, ct, activeCoordinateStatus);
					
					//Phone
					ct = commonService.getCoordinateTypeById(Constant.COORDINATE_TYPE_PRIMARY_PHONE);
					setPersonBeanCoordinate(person, personBean, ct, activeCoordinateStatus);
					
					ct = commonService.getCoordinateTypeById(Constant.COORDINATE_TYPE_SECONDARY_PHONE);
					setPersonBeanCoordinate(person, personBean, ct, activeCoordinateStatus);
					
					//email
					ct = commonService.getCoordinateTypeById(Constant.COORDINATE_TYPE_PRIMARY_EMAIL);
					setPersonBeanCoordinate(person, personBean, ct, activeCoordinateStatus);
					
					//facebook id
					ct = commonService.getCoordinateTypeById(Constant.COORDINATE_TYPE_FACEBOOK_ID);
					setPersonBeanCoordinate(person, personBean, ct, activeCoordinateStatus);

					result.add(personBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private void setPersonBeanCoordinate(Person person, PersonBean personBean,
			CoordinateType coordinateType, Status activeCoordinateStatus) {

		List<Coordinate> activeCoordinates = getCoordinateByOwnerTypeAndStatus(person, coordinateType,
				activeCoordinateStatus);
		if (activeCoordinates != null && !activeCoordinates.isEmpty()) {
			switch (coordinateType.getIdCoordinateType()) {
			case Constant.COORDINATE_TYPE_ADDRESS:
				personBean.setAddress(activeCoordinates.get(0).getCoordinate());
				break;
			case Constant.COORDINATE_TYPE_PRIMARY_PHONE:
				personBean.setPrimaryPhone(activeCoordinates.get(0).getCoordinate());
				break;
			case Constant.COORDINATE_TYPE_SECONDARY_PHONE:
				personBean.setSecondaryPhone(activeCoordinates.get(0).getCoordinate());
				break;
			case Constant.COORDINATE_TYPE_PRIMARY_EMAIL:
				personBean.setPrimaryEmail(activeCoordinates.get(0).getCoordinate());
				break;
			case Constant.COORDINATE_TYPE_FACEBOOK_ID:
				personBean.setFacebook(activeCoordinates.get(0).getCoordinate());
				break;
			default:
				break;
			}
		}
	}

	public Person getPersonById(int idPerson) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Person.class).add(Restrictions.idEq(idPerson));
			return (Person) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Gets coordinates by Owner, CoordinateType and Status
	 * 
	 * @param owner
	 * @param type
	 * @param status
	 * @return list of coordinates
	 */
	public List<Coordinate> getCoordinateByOwnerTypeAndStatus(Person owner, CoordinateType type, Status status) {
		List<Coordinate> results = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Coordinate.class).add(Restrictions.eq("owner", owner))
					.add(Restrictions.eq("type", type)).add(Restrictions.eq("status", status));
			results = (List<Coordinate>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Gets coordinates by Owner and CoordinateType
	 * 
	 * @param owner
	 * @param type
	 * @return
	 */
	public List<Coordinate> getCoordinateByOwnerType(Person owner, CoordinateType type) {
		List<Coordinate> results = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Coordinate.class).add(Restrictions.eq("owner", owner))
					.add(Restrictions.eq("type", type));
			results = (List<Coordinate>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
}
