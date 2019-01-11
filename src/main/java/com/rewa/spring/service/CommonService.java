package com.rewa.spring.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.hibernate.data.CoordinateType;
import com.rewa.hibernate.data.Diploma;
import com.rewa.hibernate.data.Field;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.PersonDiploma;
import com.rewa.hibernate.data.Role;
import com.rewa.hibernate.data.Status;

@Component
@Transactional
public class CommonService {
	private static final Logger log = Logger.getLogger(CommonService.class);
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Status getStatusByStatusName(String status) {
		if (status == null) {
			return null;
		}
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria createCriteria = session.createCriteria(Status.class);
			Status result = (Status) createCriteria.add(Restrictions.eq("status", status)).uniqueResult();
			log.debug("getStatusByStatusName (" + status + "): " + result);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	public Field getFieldById(int idField) {
		Field field = null;
		if (idField != 0) {
			try {
				// Acquire session
				Session session = sessionFactory.getCurrentSession();
				Criteria createCriteria = session.createCriteria(Field.class);
				field = (Field) createCriteria.add(Restrictions.eq("idField", idField)).uniqueResult();
				log.debug("Found field: " + field);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		return field;
	}
	
	public Field getFieldByFieldname(String fieldname) {
		Field field = null;
		if (fieldname != null) {
			try {
				// Acquire session
				Session session = sessionFactory.getCurrentSession();
				Criteria createCriteria = session.createCriteria(Field.class);
				field = (Field) createCriteria.add(Restrictions.eq("field", fieldname)).uniqueResult();
				log.debug("Found field by its name: " + field);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		return field;
	}

	public Status getStatusByStatusId(int id) {
		Status result = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria createCriteria = session.createCriteria(Status.class);
			result = (Status) createCriteria.add(Restrictions.eq("idStatus", id)).uniqueResult();
			log.debug("getStatusByStatusId (" + id + "): " + result);
		} catch (Exception e) {
			log.error(e, e);
		}
		return result;
	}

	public Role getRoleById(int roleId) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria createCriteria = session.createCriteria(Role.class);
			Role result = (Role) createCriteria.add(Restrictions.idEq(roleId)).uniqueResult();
			log.debug("getRoleById (" + roleId + "): " + result);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	public Role getRoleByRoleName(String role) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria createCriteria = session.createCriteria(Role.class);
			Role result = (Role) createCriteria.add(Restrictions.eq("role", role)).uniqueResult();
			log.debug("getRoleByRoleName (" + role + "): " + result);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	public Diploma getDiplomaByDiplomaName(String diploma) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria createCriteria = session.createCriteria(Diploma.class);
			Diploma result = (Diploma) createCriteria.add(Restrictions.eq("diploma", diploma)).uniqueResult();
			log.debug("getDiplomaByDiplomaName (" + diploma + "): " + result);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Set<Diploma> getDiplomasByPerson(Person person) {
		Set<Diploma> result = null;

		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			// Criteria criteria = session.createCriteria(PersonDiploma.class);
			List<PersonDiploma> personDiplomaList = session.createCriteria(PersonDiploma.class).list(); // criteria.add(Restrictions.eq("person",
																										// person)).list();
			if (personDiplomaList != null && !personDiplomaList.isEmpty()) {
				result = new HashSet<Diploma>();
				for (PersonDiploma personDiploma : personDiplomaList) {
					result.add(personDiploma.getDiploma());
				}
			}
			int nbResult = (result != null) ? result.size() : 0;
			log.debug("getDiplomasByPerson - Person = " + person + ", result: " + nbResult);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	public boolean userHasRole(Person person, int roleId) {
		for (Role role : person.getRoles()) {
			if (role.getIdRole() == roleId)
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getALLRoles() {
		List<Role> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			result = session.createCriteria(Role.class).list();
			int nbResult = (result != null) ? result.size() : 0;
			log.debug("getALLRoles: " + nbResult + " " + result);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Diploma> getALLDiplomas() {
		List<Diploma> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			result = session.createCriteria(Diploma.class).list();
			int nbResult = (result != null) ? result.size() : 0;
			log.debug("getALLDiplomas: " + nbResult + " " + result);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Status> getAllStatus() {
		List<Status> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			result = session.createCriteria(Status.class).list();
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

	public CoordinateType getCoordinateTypeById(int coordinateTypeId) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria createCriteria = session.createCriteria(CoordinateType.class);
			CoordinateType result = (CoordinateType) createCriteria.add(Restrictions.idEq(coordinateTypeId))
					.uniqueResult();
			log.debug("getCoordinateTypeById (" + coordinateTypeId + "): " + result);
			return result;
		} catch (Exception e) {
			log.error(e, e);
			return null;
		}
	}

}
