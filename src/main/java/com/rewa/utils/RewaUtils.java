package com.rewa.utils;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class RewaUtils {
	public static void addMessage(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static FacesMessage newBundledFacesMessage(Severity severity, String summary, Date startDate) {
		return new FacesMessage(severity, summary, startDate.toString());
	}

}
