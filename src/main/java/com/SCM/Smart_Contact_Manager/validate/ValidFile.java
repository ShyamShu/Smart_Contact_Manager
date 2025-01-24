package com.SCM.Smart_Contact_Manager.validate;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// Here We are creating a custom annotation for validating our incomming file
@Documented
@Target({ElementType.FIELD ,ElementType.ANNOTATION_TYPE , ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
// here we pass the control to the class where we implement validator to validate data 
@Constraint(validatedBy = fileValidator.class)
public @interface ValidFile {

    String message() default "invalidfile";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

  

    
} 