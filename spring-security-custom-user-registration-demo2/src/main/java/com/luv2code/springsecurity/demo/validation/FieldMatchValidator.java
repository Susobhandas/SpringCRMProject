package com.luv2code.springsecurity.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,Object>{

	String firstField;
	String SecondField;
	String message;
	
	@Override
	public void initialize(final FieldMatch constraintAnnotation) {

		firstField=constraintAnnotation.first();
		SecondField=constraintAnnotation.second();
		message=constraintAnnotation.message();
		
	}

	@Override
	public boolean isValid(final Object value,final ConstraintValidatorContext context) {
		
		boolean valid=true;
		try {
		final Object firstObj=new BeanWrapperImpl(value).getPropertyValue(firstField);
		final Object secondObj=new BeanWrapperImpl(value).getPropertyValue(SecondField);
		
		
		valid=(firstObj==null&&secondObj==null)||(firstObj!=null&&secondObj.equals(firstObj));
		}
		catch (final Exception e) {
			// TODO: handle exception
		}
		
		if(!valid) {
			context.
			buildConstraintViolationWithTemplate(message)
			.addPropertyNode(firstField)
			.addConstraintViolation()
			.disableDefaultConstraintViolation()
			;
		}
		
		return valid;
	}

}
