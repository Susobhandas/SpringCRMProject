package com.luv2code.springsecurity.demo.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { EmailValidator.class })
@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE,ElementType.FIELD })
public @interface ValidateEmail {

	String message() default "Invalid email";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default {};
	
}
