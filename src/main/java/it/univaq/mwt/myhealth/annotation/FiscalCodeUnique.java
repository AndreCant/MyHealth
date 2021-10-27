package it.univaq.mwt.myhealth.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;

import it.univaq.mwt.myhealth.annotation.validator.FiscalCodeUniqueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {FiscalCodeUniqueValidator.class})
@Documented
public @interface FiscalCodeUnique {

    String message() default "validation.fcode.exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}