package com.rewa.spring.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	public void save(Study study) {
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
	}
	
	@Transactional
	public void delete(Study study) {
		log.debug("Deleting study: " + study);
		// Acquire session
		Session session = sessionFactory.getCurrentSession();
		session.delete(study);
	}

	@SuppressWarnings("unchecked")
	public List<Study> getStudies() {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			List<Study> result = session.createCriteria(Study.class).list();
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
	public List<Study> getStudiesByStatus(Status status) {
		List<Study> result = null;
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery("Study.findByStatus").setParameter("status", status);
			result = (List<Study>) query.list();
		} catch (Exception e) {
			log.debug(e, e);
		}
		return result;
	}

	public Study getStudyById(int id) {
		try {
			// Acquire session
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Study.class).add(Restrictions.idEq(id));
			return (Study) criteria.uniqueResult();
		} catch (Exception e) {
			log.debug(e, e);
			e.printStackTrace();
			return null;
		}
	}

}
