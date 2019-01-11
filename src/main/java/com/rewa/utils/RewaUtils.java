package com.rewa.utils;

import java.util.Collections;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class RewaUtils {
	public static void addMessage(Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static Set<?> safe( Set<?> other ) {
	    return other == null ? Collections.EMPTY_SET : other;
	}

}
