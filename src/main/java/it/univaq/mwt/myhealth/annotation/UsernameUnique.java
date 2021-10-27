package it.univaq.mwt.myhealth.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import it.univaq.mwt.myhealth.annotation.validator.UsernameUniqueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {UsernameUniqueValidator.class})
@Documented
public @interface UsernameUnique {

    String message() default "validation.username.exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}