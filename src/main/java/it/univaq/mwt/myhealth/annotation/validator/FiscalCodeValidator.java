package it.univaq.mwt.myhealth.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.univaq.mwt.myhealth.annotation.FiscalCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiscalCodeValidator implements ConstraintValidator<FiscalCode, String> {

    public static final Pattern PATTERN =
            Pattern.compile("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        Matcher matcher = PATTERN.matcher(value);
        return matcher.find();
    }
}