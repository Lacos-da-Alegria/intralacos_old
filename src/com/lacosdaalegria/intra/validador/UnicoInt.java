package com.lacosdaalegria.intra.validador;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {UnicoImp.class})
@Target ({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value  = RetentionPolicy.RUNTIME)
@Documented

public @interface UnicoInt{
	
	String message() default "O elemento não é único";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
	
}