package it.univaq.mwt.myhealth.annotation.validator;

import org.springframework.beans.factory.annotation.Autowired;

import it.univaq.mwt.myhealth.annotation.EmailUnique;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || userService == null) return true;

        try {
            return !userService.existsByEmail(value);
        } catch (BusinessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}