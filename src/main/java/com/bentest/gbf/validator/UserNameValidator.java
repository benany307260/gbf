package com.bentest.gbf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<IsUserName, String> {

	private boolean required = false;
	
	public void initialize(IsUserName constraintAnnotation) {
		required = constraintAnnotation.required();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {
			return true;
		}else {
			return false;
		}
	}

}
