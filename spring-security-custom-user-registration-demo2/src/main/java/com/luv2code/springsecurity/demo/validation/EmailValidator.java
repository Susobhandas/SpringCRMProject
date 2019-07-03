package com.luv2code.springsecurity.demo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<ValidateEmail, String> {

	private Pattern pattern;
	private Matcher matcher;
	
	public final String EMAIL_PATTERN="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {
		
		pattern=Pattern.compile(EMAIL_PATTERN);
		if(email==null) {
			return false;
		}
		
		matcher=pattern.matcher(email);
		return matcher.matches();
	}

}
