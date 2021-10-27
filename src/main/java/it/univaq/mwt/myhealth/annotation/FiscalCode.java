package it.univaq.mwt.myhealth.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import it.univaq.mwt.myhealth.annotation.validator.FiscalCodeValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {FiscalCodeValidator.class})
@Documented
public @interface FiscalCode {

    String message() default "validation.fcode.invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}