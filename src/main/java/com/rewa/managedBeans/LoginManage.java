package com.rewa.managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rewa.spring.service.LoginService;
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
	public String validateUsernamePassword() {
		log.debug("validateUsernamePassword: " + user + "/" + pwd);
		boolean valid = loginService.validate(user, pwd);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Utilisateur ou mot de passe incorrect", "Veuillez ré-essayer ou contacter un administrateur"));
			return "login";
		}
	}

	public String logoutAction() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		RewaUtils.addMessage(FacesMessage.SEVERITY_INFO, "Déconnexion effectuée avec succès !", null);
		return "login";
	}

	public boolean isLoggedIn() {
		return user != null;
	}

	public String isLoggedInForwardHome() {
		if (isLoggedIn()) {
			return "admin";
		}

		return null;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
