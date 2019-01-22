package com.rewa.managedBeans;

import java.io.Serializable;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rewa.beans.PersonBean;
import com.rewa.constant.Constant;
import com.rewa.hibernate.data.Person;
import com.rewa.hibernate.data.Role;
import com.rewa.spring.service.LoginService;
import com.rewa.utils.PersonUtils;
import com.rewa.utils.RewaUtils;
import com.rewa.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class LoginManage implements Serializable {
	private static final Logger log = Logger.getLogger(LoginManage.class);

	private static final long serialVersionUID = 1094801825228386363L;

	private String pwd;
	private String msg;
	private String user;
	private Person connectedUser;
	private PersonBean connectedUserBean;
	
	/**** Application roles***/
	private boolean isAdmin;

	@ManagedProperty("#{loginService}")
	private LoginService loginService;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	// validate login
	public String loginActionEvent() {
		log.debug("validateUsernamePassword: " + user + "/" + pwd);
		connectedUser = loginService.validate(user, pwd);
		if (connectedUser != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			session.setAttribute("connectedUser", connectedUser);
			connectedUserBean = PersonUtils.getPersonBeanByPerson(connectedUser, true);
			return "agent";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Utilisateur ou mot de passe incorrect", "Veuillez ré-essayer ou contacter un administrateur"));
			return "login";
		}
	}

	public String logoutAction() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		RewaUtils.addMessage(FacesMessage.SEVERITY_INFO, "Déconnexion effectuée avec succes !", null);
		return "login";
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public Person getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(Person connectedUser) {
		this.connectedUser = connectedUser;
	}

	public boolean isAdmin() {
		if (connectedUser != null) {
			Set<Role> roles = connectedUser.getRoles();
			if (roles != null && !roles.isEmpty())
				for (Role role : roles) {
					if (role.getIdRole() == Constant.ADMIN_ROLE_ID)
						this.isAdmin = true;
				} 
		}
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public PersonBean getConnectedUserBean() {
		return connectedUserBean;
	}

	public void setConnectedUserBean(PersonBean connectedUserBean) {
		this.connectedUserBean = connectedUserBean;
	}
}
