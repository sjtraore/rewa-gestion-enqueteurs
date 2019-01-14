package com.rewa.utils;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.calendar.Calendar;

@FacesValidator("dateRangeValidator")
public class DateRangeValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		// Leave the null handling of startDate to required="true"
		Object startDateValue = component.getAttributes().get("startDate");
		if (startDateValue == null) {
			return;
		}

		Date startDate = (Date) startDateValue;
		Date endDate = (Date) value;
		if (endDate.before(startDate)) {
			throw new ValidatorException(RewaUtils.newBundledFacesMessage(FacesMessage.SEVERITY_ERROR,
					((Calendar) component).getLabel(), startDate));
		}
	}
}
