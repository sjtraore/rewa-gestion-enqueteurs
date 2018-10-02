package com.rewa.spring.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Status getStatusByStatusName(String status) {
		if(status == null) {
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
}
