package com.demo.student.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.validation.*;


public class ValidatorUtil {
	// final static Logger logger = Logger.getLogger(ValidatorUtil.class);

	public static <S extends org.springframework.validation.Validator, T> List<String> validate(T objectToValidate, S customValidator){
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		jakarta.validation.Validator validator = validatorFactory.getValidator();
		
		List<String> errorList = new ArrayList<>();
		Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

		List<String> validationError = new ArrayList<String>();

		for (ConstraintViolation<T> violation : violations) {
		    String propertyPath = violation.getPropertyPath().toString();
		    String message = violation.getMessage();
		    errorList.add(propertyPath+" : "+message);    
		}

		checkCustomValidation(objectToValidate, customValidator, errorList);

		if(errorList.size() > 0){
		return errorList;
		}
		else {
			return null;
		}


	}

	private static <S extends org.springframework.validation.Validator, T> void checkCustomValidation(T objectToValidate, 
			S customValidator, List<String> errorList) {

		Errors errors = new BindException(objectToValidate, objectToValidate.getClass().getName());
		customValidator.validate(objectToValidate, errors);

		if(errors.hasErrors()){
			 List<FieldError> fError = errors.getFieldErrors();
			 for(FieldError error : fError){
				 errorList.add(error.getField() + " : " + error.getDefaultMessage());
			 }
		}
	}
	
	
	

}