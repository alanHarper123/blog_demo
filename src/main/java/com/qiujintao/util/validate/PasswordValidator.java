package com.qiujintao.util.validate;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
	
	private static final String PASSWORD_REGEX = "^(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9@#$%^&+=]).{6,}$";
	private Pattern pattern = Pattern.compile(PASSWORD_REGEX);
	@Override
	public void initialize(ValidPassword arg0) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		return pattern.matcher(email).matches();
	}

}