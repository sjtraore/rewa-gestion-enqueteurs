package com.rewa.spring.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.PersonStudy;
import com.rewa.hibernate.data.Status;
import com.rewa.hibernate.data.Study;

@Component
@Transactional
public class StudyService {
	private static final Logger log = Logger.getLogger(StudyService.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public Study save(Study study) {
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		study.setCreateDate(new Date());
		// saving behavior get done in a transactional manner
		if (study.getIdStudy() == 0) {
			study.setCreateDate(new Date());
			session.save(study);
		} else {
			session.merge(study);
		}
		return study;
	}

	@Transactional
	public void delete(Study study) {
		log.debug("Deleting study: " + study);
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		session.delete(study);
	}

	@SuppressWarnings("unchecked")
	public Set<Study> getStudies() {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Set<Study> result = new HashSet<Study>(session.createCriteria(Study.class).list());
			return result;
		} catch (Exception e) {
			log.debug(e, e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * List of active (none closed) studies
	 * 
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<Study> getStudiesByStatus(Status status) {
		Set<Study> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery("Study.findByStatus").setParameter("status", status);
			result = new HashSet<Study>(query.list());
		} catch (Exception e) {
			log.debug(e, e);
		}
		return result;
	}

	public PersonStudy getPersonStudyById(int idPersonStudy) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(PersonStudy.class)
					.add(Restrictions.eq("idPersonStudy", idPersonStudy));
			return (PersonStudy) criteria.uniqueResult();
		} catch (Exception e) {
			log.debug(e, e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Set<PersonStudy> getPersonStudyListByPersonAndStudyStatus(Person person, Status studyStatus) {
		Set<PersonStudy> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery("PersonStudy.findByPersonAndStudyStatus")
					.setParameter("person", person)
					.setParameter("studyStatus", studyStatus);
			result = new HashSet<PersonStudy>(query.list());
		} catch (Exception e) {
			log.debug(e, e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Set<PersonStudy> getPersonStudyListByStudy(Study study) {
		Set<PersonStudy> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery("PersonStudy.findByStudy").setParameter("study", study);
			result = new HashSet<PersonStudy>(query.list());
		} catch (Exception e) {
			log.debug(e, e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Set<PersonStudy> getPersonStudyListByPersonAndStudy(Person person, Study study) {
		Set<PersonStudy> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery("PersonStudy.findByPersonAndStudy").setParameter("study", study)
					.setParameter("person", person);
			result = new HashSet<PersonStudy>(query.list());
		} catch (Exception e) {
			log.debug(e, e);
		}
		return result;
	}

	// Compatibility
	public Study getStudyById(int id) {
		return getStudyById(id, true);
	}

	public Study getStudyById(int id, boolean lazyMode) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Study result = null;
			if (lazyMode) {
				Criteria criteria = session.createCriteria(Study.class).add(Restrictions.idEq(id));
				result = (Study) criteria.uniqueResult();
			} else {
				Query query = session.getNamedQuery("Study.findByIdEagerMode").setParameter("idStudy", id);
				result = (Study) query.uniqueResult();
			}
			return result;
		} catch (Exception e) {
			log.debug(e, e);
			return null;
		}
	}

	@Transactional
	public void savePersonStudy(PersonStudy personStudy) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			if(personStudy.getIdPersonStudy() != 0) {
				session.merge(personStudy);
			} else {
				session.save(personStudy);
			}
		} catch (Exception e) {
			log.debug(e, e);
		}
	}

	@Transactional
	public void deletePersonStudy(PersonStudy personStudy) {
		log.debug("Deleting personStudy: " + personStudy);
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		session.delete(personStudy);
	}

}
